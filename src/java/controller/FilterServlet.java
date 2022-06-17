/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author HHPC
 */
public class FilterServlet extends HttpServlet {

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
            out.println("<title>Servlet FilterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterServlet at " + request.getContextPath() + "</h1>");
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
        ArrayList<Product> products = null;
        try {
            ProductsDAO db = new ProductsDAO();
            String priceString = request.getParameter("price");
            if (priceString != null) {
                products = db.getProductsSearchingByPrice(Double.parseDouble(priceString));
            } else {
                String search = request.getParameter("search");
                System.out.println(search);
                System.out.println("select * from products where name like '%" + search + "%'");
                products = db.getProductsSearching(search);
            }
            if (products != null) {
                for (Product product : products) {
                    out.println("<div class=\"col-lg-3 d-flex align-items-stretch\" style=\"width: 15rem\">\n"
                            + "                                    <a href=\"product?id=" + product.getId() + "\" class=\"card bg-dark\" style=\"width:100%; text-decoration: none;\">\n"
                            + "                                        <img src=\"" + product.getImgLink() + "\" class=\"card-img-top img-thumbnail\" alt=\"...\" >\n"
                            + "                                        <div class=\"card-body\">\n"
                            + "                                            <h6 class=\"card-subtitle mb-2 text-muted text-center text-capitalize\" style=\"font-size: 10px\">" + product.getBrand() + "</h6>\n"
                            + "                                            <h5 class=\"card-title text-capitalize text-center text-white\" style=\"font-size: 12px\">" + product.getName() + "</h5>\n"
                            + "                                            <h5 class=\"card-text text-capitalize text-center text-white-50\" style=\"font-size: 20px\">$" + product.getPrice() + "</h5>\n"
                            + "                                        </div>\n"
                            + "                                    </a>\n"
                            + "                                </div> ");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FilterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
