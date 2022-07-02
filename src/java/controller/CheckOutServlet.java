/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
import DAO.HistoryDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.History;
import model.Item;
import model.User;

/**
 *
 * @author HHPC
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/checkout"})
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
            int userId = getUserId(request, response);
            CartDAO cartDAO = new CartDAO();
            Cart cart = cartDAO.getCart(userId);
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
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
                String data = getDataString(cart);
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
            String data = getDataString(cart);
            out.println(data);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String checkoutEmail = (String) request.getParameter("checkout-email");
            String checkoutPhone = (String) request.getParameter("checkout-phone");
            String checkoutName = (String) request.getParameter("checkout-name");
            String checkoutCity = (String) request.getParameter("checkout-city");
            String checkoutDistrict = (String) request.getParameter("checkout-district");
            String checkoutWard = (String) request.getParameter("checkout-ward");
            String checkoutAddress = (String) request.getParameter("checkout-address");
            String checkoutDelivery = (String) request.getParameter("checkout-delivery");
            String checkoutMethod = (String) request.getParameter("checkout-method");
            int userId = getUserId(request, response);
            CartDAO cartDAO = new CartDAO();
            Cart cart = cartDAO.getCart(userId);
            double feeShipping = checkoutDelivery.equalsIgnoreCase("express") ? 6 : 2;
            double totalCostHistory = cart.getTotalCostCart();
            totalCostHistory +=feeShipping;
            History history = new History(userId,checkoutEmail, checkoutPhone,checkoutName, checkoutCity, checkoutDistrict, checkoutWard, checkoutAddress, checkoutDelivery, checkoutMethod,  totalCostHistory);
            for (Item item : cart.getCart()) {
                history.getProductIdList().put(item.getProduct(), item.getQuantity());
            }
            request.getSession().setAttribute("historyConfirm", history);
            request.getSession().setAttribute("cartConfirm", cart);
            request.getRequestDispatcher("confirmCheckout.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDataString(Cart cart){
        String data = "<div class=\"d-flex flex-column \" style=\"height: 500px; overflow-y:  scroll; overflow-x: hidden\">";
        for (Item item : cart.getCart()) {
            data += " <div class=\"row\">\n" +
"                                    <div class=\"col-lg-10 d-flex\">\n" +
"                                    <div class=\"\" style=\"width: 94px; height: 94px; margin-right: 5px\">\n" +
"                                        <img src=\""+item.getProduct().getImgLink()+"\" alt=\"alt\" class=\"rounded\" style=\"width: 120px; height: 120px\">\n" +
"                                    </div>\n" +
"                                    <div class=\"text-capitalize text-white ms-5\">\n" +
"                                        <h5 class=\"fw-light fst-italic \">"+item.getProduct().getName()+"</h5>\n" +
"                                        <h6 class=\" fw-lighter\">"+item.getProduct().getBrand()+"</h6>\n" +
"                                        <h6 class=\" fw-bolder\">$"+item.getProduct().getPrice()+"</h6>\n" +
"                                    </div>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-lg-2 d-flex flex-column justify-content-between align-items-center\" style=\"height: 120px\">\n" +
"                                        <button style=\"background: transparent\"><a data-product-id=\""+item.getProduct().getId()+"\" class=\"text-decoration-none\" onclick=\"deleteProductCheckout(this)\">Remove</a></button>\n" +
"                                        <input type=\"number\" data-product-id=\""+item.getProduct().getId()+"\" value=\""+item.getQuantity()+"\" style=\"width: 36px\" class=\"text-center\" onclick=\"changeQuantityCheckout(this)\">\n" +
"                                        <p class=\"text-white\">$"+item.getTotalCost()+"</p>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                    <hr style=\"color: #fff\" class=\"mt-3\">";
        }
        data += "</div><div class=\"d-flex flex-column text-white mt-5\">\n" +
"                                <div class=\"d-flex justify-content-between\">\n" +
"                                    <h5>Subtotal</h5>\n" +
"                                    <p class=\"checkout-cart-total\">$"+cart.getTotalCostCart()+"</p>\n" +
"                                </div>\n" +
"                                <div class=\"d-flex justify-content-between\">\n" +
"                                    <h5>Shipping</h5>\n" +
"                                    <p class=\"checkout-fee-shipping\"></p>\n" +
"                                </div>\n" +
"                                <div class=\"d-flex justify-content-between mt-3\">\n" +
"                                    <h4>Total</h4>\n" +
"                                    <h5 class=\"checkout-total\"></h5>\n" +
"                                </div>\n" +
"                            </div>";
        return data;
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
