/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAO.BookingDAO;
import com.sun.source.tree.Tree;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Booking;

/**
 *
 * @author Admin
 */
public class BookingAdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public boolean checkRole(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("role_admin") == null || !session.getAttribute("role_admin").equals("admin")) {
            return false;
        }
        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param bookings
     * @param request servlet request
     * @param response servlet response
     * @return
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkRole(request, response)) {
            response.sendRedirect("GetProductsHomeServlet");
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date date = new Date();  
            String today =formatter.format(date);  
            String filter = null;
            if (request.getParameter("filter") != null) {
                  filter = request.getParameter("filter");
            }
            try {
                BookingDAO bookingDAO = new BookingDAO();
                ArrayList<Booking> bookings = bookingDAO.getBookings(filter, today);
                if (!bookings.isEmpty()) {
                    TreeMap<String, ArrayList<Booking>> bookingMap = classifyBooking(bookings);         
                    request.setAttribute("bookings", bookingMap);
                } else {
                    request.setAttribute("msgBooking", "The booking list is empty");
                }
                request.getRequestDispatcher("bookingAdmin.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(BookingAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String bookingPhone = request.getParameter("booking-phone");
            String bookingName = request.getParameter("booking-name");
            String bookingService = request.getParameter("booking-service");
            String bookingDay = request.getParameter("booking-date");
            String bookingTime = request.getParameter("booking-time");
            String bookingNote = "";
            if (request.getParameter("booking-note") != null) {
                bookingNote = request.getParameter("booking-note");
            }
            String bookingDate = bookingDay + " " + bookingTime + ":00";
            Booking booking = new Booking(bookingPhone, bookingName, bookingService, bookingDate, bookingNote);
//            System.out.println(booking);
            BookingDAO bookingDAO = new BookingDAO();

            if (bookingDAO.insertBooking(booking)) {
                out.println("Thanks for your booking");

            } else {
                response.setStatus(500);
                out.flush();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @param bookings
     * @return a String containing servlet description
     */
    public TreeMap<String, ArrayList<Booking>> classifyBooking(ArrayList<Booking> bookings) {
        TreeMap<String, ArrayList<Booking>> bookingMap = new TreeMap<>();

        for (Booking booking : bookings) {
            String date = booking.getBookingDate().split(" ")[0];
            if (!bookingMap.containsKey(date)) {
                bookingMap.put(date, new ArrayList<Booking>());
            }
        }

        for (String key : bookingMap.keySet()) {
            for (Booking booking : bookings) {
                String dateBooking = booking.getBookingDate().split(" ")[0];
                if (dateBooking.equalsIgnoreCase(key)) {
                    bookingMap.get(key).add(booking);
                }
            }
        }
        return bookingMap;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
