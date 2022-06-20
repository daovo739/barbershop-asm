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
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        try {
            String category;
            ArrayList<String> filtering = new ArrayList<>();
            if (request.getParameter("category") != null) {
                category = (String) request.getParameter("category").toLowerCase();
                if (!category.equalsIgnoreCase("all")) {
                    filtering.add(category);
                }
            } else {
                category = "all";
                ;
            }

            ProductsDAO db = new ProductsDAO();
            ArrayList<Product> products = db.getProducts(category);
            int limit = 8, page = 1;
            if (request.getParameter("page") != null) { 
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("sort") != null) {
                String sort = request.getParameter("sort");
                String sortFiltering = null;
                if (sort.equals("asc")) {
                    Collections.sort(products, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return (int) (o1.getPrice() - o2.getPrice());
                        }
                    });    
                    sortFiltering = "ascending";
                }
                 if (sort.equals("dsc")) {
                    Collections.sort(products, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return (int) (o2.getPrice() - o1.getPrice());
                        }
                    });  
                     sortFiltering = "descending";
                }
                 request.setAttribute("sort", sort);
                 filtering.add(sortFiltering);
            }
            int totalPage;
            int totalRow = products.size();
            if (totalRow % limit == 0) { 
                totalPage = totalRow / limit;
            } else {
                totalPage = totalRow / limit + 1;
            }
            ArrayList<Product> newProducts = new ArrayList<>();
            for (int i = (page - 1) * limit; i < page * limit; i++) {
                if (i >= totalRow) {
                    break;
                }
                newProducts.add(products.get(i));
            }
            if (products == null) {
                request.setAttribute("msg", "Product List Is Empty");
                request.getRequestDispatcher("products.jsp").forward(request, response);
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
            session.setAttribute("landingPage", "products");
            request.getRequestDispatcher("products.jsp").forward(request, response);
//            response.sendRedirect("products.jsp");
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
