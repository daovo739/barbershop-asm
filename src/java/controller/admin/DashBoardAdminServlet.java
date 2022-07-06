/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAO.DashBoardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Booking;
import model.DashBoard;
import model.Item;

/**
 *
 * @author HHPC
 */
public class DashBoardAdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashBoardAdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoardAdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkRole(request, response)) {
            response.sendRedirect("GetProductsHomeServlet");
        } else {
            try {
                 String from = null, to = null;
                if (request.getParameter("dashboard-from") != null) {
                    from = request.getParameter("dashboard-from");
                }
                if (request.getParameter("dashboard-to") != null) {
                    to = request.getParameter("dashboard-to");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c = Calendar.getInstance();
                    c.setTime(sdf.parse(to));
                    c.add(Calendar.DATE, 1);
                    to = sdf.format(c.getTime());
                }

                String today = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
                String tomorrow = LocalDateTime.now().plusDays(2).format(DateTimeFormatter.ISO_DATE);
                DashBoardDAO dashBoardDAO = new DashBoardDAO();
                int userCount = dashBoardDAO.getNumberUsers();
                int orderCount = dashBoardDAO.getNumberHistories();
                int bookingCount = dashBoardDAO.getNumberHBooking();
                double totalCost = Math.round(dashBoardDAO.getTotalCost(from, to) * 100.0) / 100.0;
                Item bestSale = dashBoardDAO.getBestSeller();
                ArrayList<Booking> bookings = dashBoardDAO.getBookings(today, tomorrow);
                DashBoard dashboard = new DashBoard(userCount, orderCount, bookingCount, totalCost, bestSale);
                request.setAttribute("dashboard", dashboard);
                if (bookings.isEmpty()) {
                    request.setAttribute("msg", "No bookings today and tomorrow");
                } else {
                    request.setAttribute("bookings", bookings);
            }
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(DashBoardAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(DashBoardAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
