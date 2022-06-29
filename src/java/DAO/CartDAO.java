/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Item;
/**
 *
 * @author HHPC
 */
public class CartDAO {
    private final Connection conn;
    private final Statement stm;
    private ResultSet rs;
    
    public CartDAO() throws SQLException {
        conn = new ConnectDB().getConn();
        stm = conn.createStatement();
    }
    
    public boolean isExsist(int userId){
        try {
            String sql = "Select * from cart where user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    
    public int getCartIdByUserId(int userId){
        try {
            String sql = "Select cart_id from cart where user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
             return 0;
        }
    }
    
    public boolean insertCart(Item item) {
        int productId = item.getProductId();
        int userId = item.getUserId();
        int quantity = item.getQuantity();
        String sql;
        try {
            if (isExsist(userId)) {
                int cartId = getCartIdByUserId(userId);
                sql = "update cart_contains set quantity = quantity + " + quantity + " where product_id = " + productId + " and cart_id = " + cartId;
                if (stm.executeUpdate(sql) <= 0) {
                    sql = "insert into cart_contains values(" + cartId + "," + productId + "," + quantity + ")";
                    stm.executeUpdate(sql);
                }
            } else {
                sql = "insert into cart values(" + userId + ")";
                stm.executeUpdate(sql);
                 int cartId = getCartIdByUserId(userId);
                sql = "insert into cart_contains values(" + cartId + "," + productId + ","+quantity+")";
                stm.executeUpdate(sql);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Cart getCart(int userId){
        try {
            String sql = "SELECT * FROM ((products INNER JOIN cart_contains ON products.product_id = cart_contains.product_id) INNER JOIN cart ON cart_contains.cart_id = cart.cart_id) where user_id = " + userId;
            rs = stm.executeQuery(sql);
            Cart cart = new Cart();
            while(rs.next()){
                cart.getCart().add(new Item(userId, rs.getInt("quantity"), rs.getInt("product_id")));
            }
            return cart;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getTotalRows(int cartId){
        try {
            String sql = "SELECT COUNT(1) FROM  cart_contains where cart_id = " + cartId ;
            rs = stm.executeQuery(sql);
            rs.next();
            int total = rs.getInt(1);
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean updateQuantity(int quantity, int cartId, int productId){
        try {
            String sql = "update cart_contains set quantity = " + quantity + " where product_id = " + productId + " and cart_id = " + cartId;
            stm.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deleteProduct(int productId){
        try {
            String sql = "Delete FROM  cart_contains where product_id = " + productId ;
            stm.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String getDataString(Cart cart) {
        String data;
        if (cart.getCart().isEmpty()) {
            data = "<div class=\"offcanvas-header\">\n"
                    + "        <h5 class=\"offcanvas-title\" id=\"offcanvasWithBothOptionsLabel\">Your Cart</h5>\n"
                    + "        <button type=\"button\" class=\"btn-close text-reset btn-close-white\" data-bs-dismiss=\"offcanvas\" aria-label=\"Close\"></button>\n"
                    + "    </div>\n"
                    + "        <div class=\"offcanvas-body h-25   cart-list\" style=\"overflow-x: hidden\"><h2 class=\"text-white\">Your cart is empty. Shopping now!</h2>\n"
                    + "        </div>";
        } else {
            data = "<div class=\"offcanvas-header\">\n"
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
                                + "                        <input type=\"number\" data-product-id = \"" + item.getProduct().getId() + "\" value=\"" + item.getQuantity() + "\" style=\"width: 36px\" class=\"text-center\" onclick=\"changeQuantity(this)\">\n"
                        + "                        <button style=\"background: transparent\"><a data-product-id = \"" + item.getProduct().getId() + "\" class=\"text-decoration-none\" onclick=\"deleteProduct(this)\">Remove</a></button>\n"
                        + "                    </div>\n"
                        + "                    <hr style=\"margin-top: 12px\">\n";
            }
            data += "</div>\n"
                    + "    </div>\n"
                    + "    <div class=\"bottom d-flex flex-column\" style=\"padding: 0 16px;margin-bottom: 16px\">\n"
                    + "        <div class=\"d-flex justify-content-between\">\n"
                    + "            <p class=\"fs-3 text\">Total</p>\n"
                    + "            <p class=\"fs-3 text fw-bold\">$" + cart.getTotalCostCart() + "</p>\n"
                    + "        </div>\n"
                    + "        <button class=\"btn btn-primary\"><a  class=\"text-white text-decoration-none\" href=\"checkout\">Checkout</a></button>\n"
                    + "    </div>";
        }
        return data;
    }
}
