/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author HHPC
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
            UserDAO userDAO = new UserDAO();
            CartDAO cartDAO = new CartDAO();
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            if (userName.equals("admin") && password.equals("admin")) {
                request.getSession().setAttribute("role_admin", "admin");
                response.sendRedirect("dashboardAdmin");
            } else {
                User user = new User(userName, password);
                User userLogin = userDAO.checkLogin(user);
                if (userLogin != null) {
                    Cookie userCookie = new Cookie("userName", userLogin.getUserName());
                    userCookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(userCookie);
                    Cookie idCookie = new Cookie("userId", Integer.toString(userLogin.getId()));
                    idCookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(idCookie);
                    int countProduct = cartDAO.getTotalRows(cartDAO.getCartIdByUserId(userLogin.getId()));
                    request.getSession().setAttribute("cartCount", countProduct);
                    request.getSession().setAttribute("userCurrent", userLogin);
                    request.getRequestDispatcher("GetProductsHomeServlet").forward(request, response);
                } else {
                    request.setAttribute("msg", "User name or password is incorrect");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
