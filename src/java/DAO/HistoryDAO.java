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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.History;
import model.Item;

/**
 *
 * @author HHPC
 */
public class HistoryDAO {
     private final Connection conn;
    private final Statement stm;
    private ResultSet rs;
    
    public HistoryDAO() throws SQLException {
        conn = new ConnectDB().getConn();
        stm = conn.createStatement();
    }
    
    public boolean insertHistory(History history){
         try {
             String sql = "insert into history values(?,?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, history.getUserId());
             ps.setString(2, history.getCreateAt());
             ps.setDouble(3, history.getTotalCostHistory());
             ps.setString(4, history.getEmail());
             ps.setString(5, history.getPhone());
             ps.setString(6, history.getName());
             ps.setString(7, history.getCity());
             ps.setString(8, history.getDistrict());
             ps.setString(9, history.getWard());
             ps.setString(10, history.getAddress());
             ps.setString(11, history.getPaymentMethod());
             ps.setString(12, history.getDeliveryMethod());
             ps.execute();
             return true;
         } catch (SQLException ex) {
             Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
    }
    
    public int getHistoryId(History history, int userId){
         try {
             Timestamp timestamp = Timestamp.valueOf(history.getCreateAt());
            String sql = "Select history_id from history where user_id = ? and date = ?";            
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, userId);
             ps.setTimestamp(2, timestamp);
             rs = ps.executeQuery();
             rs.next();
             return rs.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
             return -1;
         }     
    }
    
    public boolean insertHistoryContains(Item item, int historyId){
         try {
             String sql = "insert into history_contains values(?,?,?)";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, historyId);
             ps.setInt(2, item.getProductId());
             ps.setInt(3, item.getQuantity());
             ps.execute();
             return true;
         } catch (SQLException ex) {            
             Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
    }
    
    public ArrayList<History> getHistoriesAllowUserId(int userId){
        ArrayList<History> histories = new ArrayList<>();
         try {
             String sql = "select * from history where user_id = " + userId;
             rs = stm.executeQuery(sql);
             while(rs.next()){
                 int historyId = rs.getInt(1);
                 Timestamp date= rs.getTimestamp(3);
                 double totalCostHistory = rs.getDouble(4);
                 String email = rs.getString(5);
                 String phone = rs.getString(6);
                 String name = rs.getString(7);
                 String city = rs.getString(8);
                 String district = rs.getString(9);
                 String ward = rs.getString(10);
                 String address = rs.getString(11);
                 String deliveryMethod = rs.getString(12);
                 String paymentMethod = rs.getString(13);
                 histories.add(new History(historyId, userId, date, email, phone, name, city, district, ward, address, deliveryMethod, paymentMethod, totalCostHistory));
             }
             return histories;
         } catch (SQLException ex) {
             Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
    }
    
    public ArrayList<History> getProductsHistories(int userId){
        ArrayList<History> histories = getHistoriesAllowUserId(userId);
        String sql;
        for (History history : histories) {
            try {
                ProductsDAO productsDAO = new ProductsDAO();
                int historyId = history.getHistoryId();
                sql = "select history.history_id, product_id, quantity from  history  inner join history_contains on history.history_id = history_contains.history_id where user_id =  " + userId + " and history_id = " +historyId;
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    int productId = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    history.getProductIdList().put(productsDAO.getProductById(productId), quantity);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return histories;
        
    }
}
