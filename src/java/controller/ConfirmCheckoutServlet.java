/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.HistoryDAO;
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
import model.Cart;
import model.History;
import model.Item;

/**
 *
 * @author HHPC
 */
public class ConfirmCheckoutServlet extends HttpServlet {

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
            out.println("<title>Servlet ConfirmCheckoutServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmCheckoutServlet at " + request.getContextPath() + "</h1>");
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
       processRequest(request, response);
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
            HttpSession session = request.getSession();
        try {         
            History history = (History) session.getAttribute("historyConfirm");
            Cart cart = (Cart) session.getAttribute("cartConfirm");
            int userId = getUserId(request, response);
            CartDAO cartDAO = new CartDAO();
            HistoryDAO historyDAO = new HistoryDAO();
            int cartId = cartDAO.getCartIdByUserId(userId);
            boolean insertSuccess = true;
            if (!historyDAO.insertHistory(history)) {
                insertSuccess = false;
            }
            int historyId = historyDAO.getHistoryId(history, userId);
            for (Item item : cart.getCart()) {
                if (!historyDAO.insertHistoryContains(item, historyId)) {
                    insertSuccess = false;
                }
            }
            if (insertSuccess) {
                cartDAO.deleteCart(cartId);
                session.setAttribute("cartCount", "0");
                request.setAttribute("checkoutSuccess", "Thanks for your ordering!");
                request.getRequestDispatcher("confirmCheckout.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            session.removeAttribute("historyConfirm");
            session.removeAttribute("cartConfirm");
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
