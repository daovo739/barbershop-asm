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
        try {
            HttpSession session = request.getSession();
            String category = (String) request.getParameter("category").toLowerCase();
            ProductsDAO db = new ProductsDAO();
            ArrayList<Product> products = db.getProducts(category);
            int limit = 8, page = 1;
            if (request.getParameter("page") != null) { 
                page = Integer.parseInt(request.getParameter("page")); 
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
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            int prevPage = page -1;
            int nextPage = page + 1;
            prevPage = prevPage == 0 ? totalPage : prevPage;
            nextPage = nextPage > totalPage ? 1 : nextPage;            
            session.setAttribute("products", newProducts);
            session.setAttribute("totalPage", totalPage);
            session.setAttribute("categories", db.getCategories());
            session.setAttribute("category", category);
            session.setAttribute("nextPage", nextPage);
            session.setAttribute("prevPage", prevPage);
            session.setAttribute("pageCurrent", page);
            response.sendRedirect("products.jsp");
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
