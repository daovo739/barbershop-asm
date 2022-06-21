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
    
    public int getCurrentId(String table, String column){
        try {
            String sql = "select top 1 ? from ? order by ? desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, column);
            ps.setString(2, table);
            ps.setString(3, column);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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
                sql = "insert into cart_contains values(" + cartId + "," + productId + ","+quantity+")";
                stm.executeUpdate(sql);
            } else {
                int cartId = getCurrentId("cart", "cart_id") + 1;
                sql = "insert into cart values(" + cartId + "," + userId + ")";
                stm.executeUpdate(sql);
                sql = "insert into cart_contains values(" + cartId + "," + productId + ","+quantity+")";
                stm.executeUpdate(sql);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
