/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CartDAO;
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
import model.Item;

/**
 *
 * @author HHPC
 */
public class GetCartServlet extends HttpServlet {

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
            out.println("<title>Servlet GetCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetCartServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        int userId = 0;
        Cookie[] cookies = request.getCookies();
        for (Cookie cooky : cookies) {
            if (cooky.getName().equals("userId")) {
                userId = Integer.parseInt(cooky.getValue());
                break;
            }
        }
        try {
            CartDAO cartDAO = new CartDAO();
            Cart cart = cartDAO.getCart(userId);
            if (cart.getCart().isEmpty()) {
                    out.println("<div class=\"offcanvas-header\">\n"
                            + "        <h5 class=\"offcanvas-title\" id=\"offcanvasWithBothOptionsLabel\">Your Cart</h5>\n"
                            + "        <button type=\"button\" class=\"btn-close text-reset btn-close-white\" data-bs-dismiss=\"offcanvas\" aria-label=\"Close\"></button>\n"
                            + "    </div>\n"
                            + "        <div class=\"offcanvas-body h-25   cart-list\" style=\"overflow-x: hidden\"><h2 class=\"text-white\">Your cart is empty. Shopping now!</h2>\n"
                            + "        </div>");
                } else {
                    String data = "<div class=\"offcanvas-header\">\n"
                            + "        <h5 class=\"offcanvas-title\" id=\"offcanvasWithBothOptionsLabel\">Your Cart</h5>\n"
                            + "        <button type=\"button\" class=\"btn-close text-reset btn-close-white\" data-bs-dismiss=\"offcanvas\" aria-label=\"Close\"></button>\n"
                            + "    </div>\n"
                            + "        <div class=\"offcanvas-body h-25   cart-list\" style=\"overflow-x: hidden\">\n"
                            + "        <div class=\"row\">";
                    for (Item item : cart.getCart()) {
                        data += "<div class=\"col-lg-10 d-flex\">\n"
                                + "                        <div class=\"\" style=\"width: 94px; height: 94px; margin-right: 5px\">\n"
                                + "                            <img src=\"" + item.getProduct().getImgLink() + "\" alt=\"alt\" class=\"rounded\" style=\"width: 94px; height: 94px\"/>\n"
                                + "                        </div>\n"
                                + "                        <div class=\"text-capitalize\">\n"
                                + "                            <h5 class=\"fw-light fst-italic\">" + item.getProduct().getName() + "</h5>\n"
                                + "                            <h6 class=\" fw-lighter\">" + item.getProduct().getBrand() + "</h6>\n"
                                + "                        </div>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"col-lg-2 d-flex flex-column justify-content-between align-items-center\">\n"
                                + "                        <p>$" + item.getTotalCost() + "</p>\n"
                                + "                        <input type=\"number\" value=\"" + item.getQuantity() + "\" readonly=\"true\" style=\"width: 36px\" class=\"text-center\">\n"
                                + "                        <button style=\"background: transparent\"><a data-product-id = \"" + item.getProduct().getId() + "\" class=\"text-decoration-none\" onclick=\"deleteProduct(this)\">Remove</a></button>\n"
                                + "                    </div>\n"
                                + "                    <hr style=\"margin-top: 12px\">\n";
                    }
                    data += "</div>\n"
                            + "    </div>\n"
                            + "    <div class=\"bottom d-flex flex-column\" style=\"padding: 0 16px;margin-bottom: 16px\">\n"
                            + "        <div class=\"d-flex justify-content-between\">\n"
                            + "            <p class=\"fs-3 text\">Total</p>\n"
                            + "            <p class=\"fs-3 text fw-bold\">$"+cart.getTotalCostCart()+"</p>\n"
                            + "        </div>\n"
                            + "        <button class=\"btn btn-primary\"><a  class=\"text-white text-decoration-none\">Checkout</a></button>\n"
                            + "    </div>";
                    out.println(data);
                }
        } catch (SQLException ex) {
            Logger.getLogger(GetCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
