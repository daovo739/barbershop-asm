/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Item;

/**
 *
 * @author HHPC
 */
public class AddToCartServlet extends HttpServlet {

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
            out.println("<title>Servlet AddToCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCartServlet at " + request.getContextPath() + "</h1>");
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
            boolean isHaveCooky = false;
            int userId = getUserId(request, response);
            if ( userId != -1){
                isHaveCooky = true;
            }
            if (!isHaveCooky) {
                request.setAttribute("msg", "Please login before shopping");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            int productId = Integer.parseInt(request.getParameter("id"));
            Item item = new Item(userId, quantity, productId);
            CartDAO cartDAO = new CartDAO();
            cartDAO.insertCart(item);
            int countProduct = cartDAO.getTotalRows(cartDAO.getCartIdByUserId(userId));
            request.getSession().setAttribute("cartCount", countProduct);
            PrintWriter out = response.getWriter();
            out.println(countProduct);
//            request.getRequestDispatcher("products").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param req
     * @param res
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int userId = getUserId(req, res);
            PrintWriter out = res.getWriter();
            CartDAO cartDAO = new CartDAO();
            int id = Integer.parseInt(req.getParameter("id"));
            if (cartDAO.deleteProduct(id)) {
                int countProduct = cartDAO.getTotalRows(cartDAO.getCartIdByUserId(userId));
                req.getSession().setAttribute("cartCount", countProduct);
                Cart cart = cartDAO.getCart(userId);
                String data = cartDAO.getDataString(cart);
                out.println(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int id = Integer.parseInt(req.getParameter("id"));
            PrintWriter out = resp.getWriter();
            CartDAO cartDAO = new CartDAO();
            int userId = getUserId(req, resp);
            int cartId = cartDAO.getCartIdByUserId(userId);
            if(quantity == 0 ){
                cartDAO.deleteProduct(id);
            }else{
                cartDAO.updateQuantity(quantity,cartId , id);
            }
            Cart cart = cartDAO.getCart(userId);
            String data = cartDAO.getDataString(cart);
            out.println(data);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
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
    } // </editor-fold>

}
