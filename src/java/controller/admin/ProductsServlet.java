/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAO.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Product;

/**
 *
 * @author HHPC
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ProductsServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsServlet at " + request.getContextPath() + "</h1>");
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
     * @return
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public boolean checkRole(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("role_admin") == null || !session.getAttribute("role_admin").equals("admin")) {
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkRole(request, response)) {
            response.sendRedirect("GetProductsHomeServlet");
        } else {
            try {
                HttpSession session = request.getSession();

                ProductsDAO productsDAO = new ProductsDAO();
                ArrayList<Product> products = productsDAO.getAllProducts();
                if (products == null) {
                    request.setAttribute("msg", "Product List Is Empty");
                    request.getRequestDispatcher("productsAdmin.jsp").forward(request, response);
                } else {
                    session.setAttribute("productsAdmin", products);
                    response.sendRedirect("productsAdmin.jsp");
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkRole(req, resp)) {
            resp.sendRedirect("GetProductsHomeServlet");
        } else {
            try {

                PrintWriter out = resp.getWriter();
                ProductsDAO productsDAO = new ProductsDAO();
                String name = req.getParameter("name");
                String brand = req.getParameter("brand");
                String category = req.getParameter("category");
                double price = Double.parseDouble(req.getParameter("price"));
                Part filePart = req.getPart("file");
                String fileName = filePart.getSubmittedFileName();
                String realPath = req.getServletContext().getRealPath("/static/images/products");
                String imgLink = "./static/images/products/" + fileName;

                Product product = new Product(name, brand, imgLink, "T", category, price);
                if (productsDAO.createProduct(product)) {
                    ArrayList<Product> products = productsDAO.getAllProducts();
                    String data = getDataString(products);
                    filePart.write(realPath + "\\" + fileName);
                    out.println(data);
                } else {
                    resp.setStatus(500);
                    out.flush();
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkRole(request, response)) {
            response.sendRedirect("GetProductsHomeServlet");
        } else {
            try {
                PrintWriter out = response.getWriter();
                int id = Integer.parseInt(request.getParameter("id"));
                ProductsDAO productsDAO = new ProductsDAO();
                Product product = productsDAO.getProductById(id);
                if (!request.getParameter("name").isEmpty()) {
                    product.setName(request.getParameter("name"));
                }
                if (!request.getParameter("brand").isEmpty()) {
                    product.setBrand(request.getParameter("brand"));
                }
                if (!request.getParameter("category").isEmpty()) {
                    product.setCategory(request.getParameter("category"));
                }
                if (!request.getParameter("price").isEmpty()) {
                    product.setPrice(Double.parseDouble(request.getParameter("price")));
                }
                Part filePart = request.getPart("file");
                String fileName = filePart.getSubmittedFileName();
                String realPath = request.getServletContext().getRealPath("/static/images/products");
                filePart.write(realPath + "/" + fileName);
                if (fileName != null) {
                    product.setImgLink("./static/images/products/" + fileName);
                }

                if (productsDAO.updateProduct(product)) {
                    ArrayList<Product> products = productsDAO.getAllProducts();
                    String data = getDataString(products);
                    out.println(data);
                } else {
                    response.setStatus(500);
                    out.flush();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @param products
     * @return a String containing servlet description
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkRole(req, resp)) {
            resp.sendRedirect("GetProductsHomeServlet");
        } else {
            try {
                PrintWriter out = resp.getWriter();
                int id = Integer.parseInt(req.getParameter("id"));
                ProductsDAO productsDAO = new ProductsDAO();
                if (productsDAO.deleteProduct(id)) {
                    ArrayList<Product> products = productsDAO.getAllProducts();
                    String data = getDataString(products);
                    out.println(data);
                } else {
                    resp.setStatus(500);
                    out.flush();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String getDataString(ArrayList<Product> products) {
        String data = "";
        for (Product product : products) {
            data += " <div class=\"d-flex border-bottom mt-2 justify-content-between align-items-center\">\n"
                    + "                                <div class=\"d-flex align-items-center\" style=\"width: 50%\">\n"
                    + "                                    <img src=\"" + product.getImgLink() + "\" alt=\"alt\" class=\"img-fluid\" style=\"width: 150px; height: 150px\"/>\n"
                    + "                                    <div>\n"
                    + "                                        <h5 class=\"text-capitalize\" style=\"font-size: 20px\">" + product.getName() + "</h5>\n"
                    + "                                        <h6 class=\" text-capitalize text-start\" style=\"font-size: 14px\">" + product.getBrand() + "</h6>\n"
                    + "                                         <h6 class=\" text-capitalize text-start\" style=\"font-size: 12px\">" + product.getCategory() + "</h6>\n"
                    + "                                    </div>               \n"
                    + "                                </div>\n"
                    + "                                <h5 class=\"text-capitalize\"  style=\"font-size: 26px\">$" + product.getPrice() + "</h5>\n"
                    + "                                 <div class=\"d-flex flex-column justify-content-between\">\n"
                    + "                                    <button class=\"btn btn-primary mb-2\" style=\"padding: 12px; font-size: 16px\" data-bs-toggle=\"modal\" href=\"#exampleModalToggle" + product.getId() + "\">Update</button>\n"
                    + "                                    <button class=\"btn btn-danger \" style=\"padding: 12px; font-size: 16px\" data-bs-toggle=\"modal\" href=\"#exampleModalToggleDelete" + product.getId() + "\">Delete</button>\n"
                    + "                                </div>"
                    + "                            </div>\n"
                    + "\n"
                    + "                            <div class=\"modal fade \" id=\"exampleModalToggle" + product.getId() + "\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n"
                    + "                                <div class=\"modal-dialog modal-dialog-centered \">\n"
                    + "                                    <div class=\"modal-content \">\n"
                    + "                                        <div class=\"modal-header\">\n"
                    + "                                            <h5 class=\"modal-title\" id=\"exampleModalLabel\">Update product <strong>#" + product.getId() + "</strong></h5>\n"
                    + "                                            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                    + "                                        </div>\n"
                    + "                                        <form class=\"modal-body\" id=\"form-product-" + product.getId() + "\" enctype=\"multipart/form-data\">\n"
                    + "                              \n"
                    + "                                            <input type=\"text\" id=\"name-" + product.getId() + "\" name=\"name\" placeholder=\"Enter name\" class=\"form-control mb-2\">\n"
                    + "                                            <input type=\"text\" id=\"brand-" + product.getId() + "\" name=\"brand\" placeholder=\"Enter brand\" class=\"form-control mb-2\">\n"
                    + "                                             <input type=\"text\" id=\"category-" + product.getId() + "\" name=\"category\" placeholder=\"Enter category\" class=\"form-control mb-2\">\n"
                    + "                                            <input type=\"text\" id=\"price-" + product.getId() + "\" name=\"price\" placeholder=\"Enter price\" class=\"form-control mb-2\">\n"
                    + "                                            <div class=\"input-group mb-2\">\n"
                    + "                                                <label class=\"input-group-text\" for=\"inputGroupFile01\">Upload</label>\n"
                    + "                                                <input type=\"file\" id=\"ajaxfile-" + product.getId() + "\" class=\"form-control\">\n"
                    + "                                            </div>\n"
                    + "\n"
                    + "                                        </form>\n"
                    + "                                        <div class=\"modal-footer\">\n"
                    + "                                            <button type=\"button\" class=\"btn btn-secondary close-modal\" data-bs-dismiss=\"modal\">Close</button>\n"
                    + "                                            <button data-id=\"" + product.getId() + "\" type=\"button\" class=\"btn btn-primary\" onclick=\"updateProduct(this)\">Update</button>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>"
                    + "<div class=\"modal fade \" id=\"exampleModalToggleDelete" + product.getId() + "\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n"
                    + "                                    <div class=\"modal-dialog modal-dialog-centered \">\n"
                    + "                                        <div class=\"modal-content \">\n"
                    + "                                            <div class=\"modal-header\">\n"
                    + "                                                <h5 class=\"modal-title\" id=\"exampleModalLabel\">Delete product <strong>#" + product.getId() + "</strong></h5>\n"
                    + "                                                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"modal-body\" enctype=\"multipart/form-data\">\n"
                    + "                                                <h5>Do you want to delete product <strong>" + product.getName() + "</strong>?</h5>\n"
                    + "\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"modal-footer\">\n"
                    + "                                                <button type=\"button\" class=\"btn btn-secondary close-modal\" data-bs-dismiss=\"modal\">Close</button>\n"
                    + "                                                <button data-id=\"" + product.getId() + "\" type=\"button\" class=\"btn btn-primary\" onclick=\"deleteProduct(this)\">Delete</button>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>";
        }
        return data;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
