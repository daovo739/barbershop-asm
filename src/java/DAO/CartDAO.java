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
                sql = "update cart_contains set quantity = " + quantity + " where product_id = " + productId + " and cart_id = " + cartId;
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
}
