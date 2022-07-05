package DAO;

import db.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
     public double getTotalCost(){
        try {
            String sql = "select sum(totalCost) from history ";
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
}
