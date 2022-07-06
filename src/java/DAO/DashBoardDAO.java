package DAO;

import db.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Item;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HHPC
 */
public class DashBoardDAO {
    private final Connection conn;
    private final Statement stm;
    private ResultSet rs;
    
    public DashBoardDAO() throws SQLException {
        conn = new ConnectDB().getConn();
        stm = conn.createStatement();
    }
    
    public int getNumberUsers(){
        try {
            String sql = "select count(*) from users ";
            rs = stm.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int getNumberHistories(){
        try {
            String sql = "select count(*) from history";
            rs = stm.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
     public int getNumberHBooking(){
        try {
            String sql = "select count(*) from booking";
            rs = stm.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
     public double getTotalCost(String from, String to){
         String sql;
           if (from == null && to == null){
             sql = "select sum(totalCost) from history";
         }else if (from.isEmpty() && !to.isEmpty()){
             sql = "select sum(totalCost) from history where date <= '" + to +"'";
         }else  if (!from.isEmpty() && to.isEmpty()){
             sql = "select sum(totalCost) from history where date >= '" + to +"'";
         }else{
             sql = "select sum(totalCost) from history where date >= '" + from + "' and date <= '" + to +"'";
         }
        try {
            rs = stm.executeQuery(sql);
            rs.next();
            return rs.getDouble(1);
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
     
      public Item getBestSeller(){
        try {
            String sql = "select top 1 product_id, sum(quantity)from history_contains group by product_id order by sum(quantity) desc ";
            Item item = null;
            rs = stm.executeQuery(sql);
            ProductsDAO productsDAO = new ProductsDAO();
           while(rs.next()){
               item = new Item(productsDAO.getProductById(rs.getInt(1)), rs.getInt(2));
           }
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
      
      public ArrayList<Booking>  getBookings(String from, String to){
        String sql = "SELECT * FROM booking where booking_date >= '" + from + "' and booking_date < '" +to+ "' order by booking_date asc";
        try {
            ArrayList<Booking> bookings = new ArrayList<>();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                int bookingid = rs.getInt(1);
                String bookingEmail = rs.getString(2);
                String bookingName = rs.getString(3);
                String bookingService = rs.getString(4);
                Timestamp timestamp = rs.getTimestamp(5);
                String bookingNote = rs.getString(6);
                bookings.add(new Booking(bookingid,bookingEmail, bookingName, bookingService, timestamp, bookingNote));
            }  
            return bookings;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
