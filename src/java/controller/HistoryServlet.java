/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.HistoryDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.History;
import model.User;

/**
 *
 * @author HHPC
 */
@MultipartConfig
public class HistoryServlet extends HttpServlet {

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
            out.println("<title>Servlet HistoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HistoryServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HistoryDAO historyDAO = new HistoryDAO();
            int userId = getUserId(request, response);
            ArrayList<History> histories = historyDAO.getProductsHistories(userId);
            int totalOrder = historyDAO.getTotalOrderAllowUser(userId);
            double totalCostOrder = Math.round(historyDAO.getTotalCostAllowUser(userId) * 100.0) / 100.0;
            request.setAttribute("totalOrder", totalOrder);
            request.setAttribute("totalCostOrder", totalCostOrder);
            if (histories.isEmpty()) {
                request.setAttribute("msg", "You have not purchased any products yet!");
            } else {
                request.setAttribute("histories", histories);

            }
            request.getRequestDispatcher("history.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int userId = getUserId(request, response);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);
        if (request.getParameter("newName") != null) {
            String newName = request.getParameter("newName");
            user.setName(newName);
        } else {
            Part filePart = request.getPart("newAvatar");
            String realPath = request.getServletContext().getRealPath("/static/images/avatar");
            System.out.println(filePart);
            String fileName = null;
            String avatar = "";
            if (!filePart.getSubmittedFileName().isEmpty()) {
                fileName = filePart.getSubmittedFileName();
                avatar = "./static/images/avatar/" + fileName;
                user.setAvatar(avatar);
                filePart.write(realPath + "\\" + fileName);
            }
        }
        if (userDAO.updateUser(user)) {
            request.getSession().setAttribute("userCurrent", user);
            response.sendRedirect("history");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public int getUserId(HttpServletRequest req, HttpServletResponse res){
        int userId = -1;
            Cookie[] cookies = req.getCookies();
            
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("userId")) {
                    userId = Integer.parseInt(cooky.getValue());
                    break;
                }
            }
            return userId;
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
