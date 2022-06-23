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
import java.util.Collections;
import java.util.Comparator;
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
public class GetProductsServlet extends HttpServlet {

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
            out.println("<title>Servlet GetProductsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetProductsServlet at " + request.getContextPath() + "</h1>");
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
            String category = "all";
            ArrayList<String> filtering = new ArrayList<>();
            if (request.getParameter("category") != null) {
                category = (String) request.getParameter("category").toLowerCase();
                if (!category.equalsIgnoreCase("all")) {
                    filtering.add(category);
                }
            } 
            
            String sort = "";
             if (request.getParameter("sort") != null) {
                 sort = request.getParameter("sort");
                 String sortFiltering = sort.equals("asc") ? "ascending" : "descending";         
                 request.setAttribute("sort", sort);
                 filtering.add(sortFiltering);
             }
            ProductsDAO db = new ProductsDAO();
            ArrayList<Product> products = db.getProducts(category, sort);
            if (products == null) {
                request.setAttribute("msg", "Product List Is Empty");
                request.getRequestDispatcher("products.jsp").forward(request, response);
            }
            int limit = 8, page = 1;
            if (request.getParameter("page") != null) { 
                page = Integer.parseInt(request.getParameter("page"));
            }
            int totalRow = products.size();
            int totalPage = totalRow % limit == 0 ? totalRow / limit : totalRow / limit + 1;
            ArrayList<Product> newProducts = new ArrayList<>();
            for (int i = (page - 1) * limit; i < page * limit; i++) {
                if (i >= totalRow) {
                    break;
                }
                newProducts.add(products.get(i));
            }
            int prevPage = page -1;
            int nextPage = page + 1;
            prevPage = prevPage == 0 ? totalPage : prevPage;
            nextPage = nextPage > totalPage ? 1 : nextPage;            
            request.setAttribute("products", newProducts);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("categories", db.getCategories());
            request.setAttribute("category", category);
            request.setAttribute("nextPage", nextPage);
            request.setAttribute("prevPage", prevPage);
            request.setAttribute("pageCurrent", page);
            request.setAttribute("filtering", filtering);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetProductsHomeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
          PrintWriter out = response.getWriter();
        ArrayList<Product> products = null;
        try {
            ProductsDAO db = new ProductsDAO();
            String search = request.getParameter("search");
            products = db.getProductsSearching(search);      
            if (products != null) {
                for (Product product : products) {
                    out.println("<div class=\"col-lg-3 d-flex align-items-stretch\" style=\"width: 15rem\">\n" +
"                                    <div class=\"card bg-dark\" style=\"width:100%;\">\n" +
"                                        <a  href=\"product?id="+product.getId()+"\" class=\"card-view-container text-decoration-none\">\n" +
"                                            <img src=\""+product.getImgLink()+"\" class=\"card-img-top img-thumbnail\" alt=\"...\" >  \n" +
"                                             <div class=\"card-view\">View product</div>    \n" +
"                                        </a>                                         \n" +
"                                        <div class=\"card-body d-flex flex-column justify-content-between\">\n" +
"                                            <h6 class=\"card-subtitle mb-2 text-muted text-center text-capitalize\" style=\"font-size: 10px\">"+product.getBrand()+"</h6>\n" +
"                                            <h5 class=\"card-title text-capitalize text-center text-white\" style=\"font-size: 14px\">"+product.getName()+"</h5>\n" +
"                                            <h5 class=\"card-text text-capitalize text-center text-white-50\" style=\"font-size: 20px\">$"+product.getPrice()+"</h5>\n" +
"                                        </div>\n" +
"                                        <form  class=\"d-flex justify-content-center\">\n" +
"                                            <input  data-product-id=\""+product.getId()+"\" class=\"btn btn-primary mb-3 text-white w-100\" style=\"max-width: 75%\" value=\"Add to cart\" onclick=\"addToCart(this)\"/>\n" +
"                                        </form>\n" +
"                                    </div>\n" +
"                                </div> ");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GetProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
