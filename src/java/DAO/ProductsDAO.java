/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductsDAO {
    private final Connection conn;
    private final Statement stm;
    private ResultSet rs;
    
    public ProductsDAO() throws SQLException {
        conn = new ConnectDB().getConn();
        stm = conn.createStatement();
    }
    
    public ArrayList<Product> getProducts(String categoryInput) {
        ArrayList<Product> products = new ArrayList<>();
        String sql;
        try {
            if (categoryInput.equals("all")) {
                sql = "Select * from products";
            } else {
                sql = "Select * from products where category = '"+ categoryInput +"'";
            }
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String brand = rs.getString(3);
                double price = rs.getDouble(4);
                String imgLink = rs.getString(5);
                String available = rs.getString(6);
                String category = rs.getString(7);
                products.add(new Product(id, name, brand, imgLink, available, category, price));
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Product> getRandomProducts(){
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP 9 * FROM products ORDER BY NEWID()";
            rs = stm.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String brand = rs.getString(3);
                double price = rs.getDouble(4);
                String imgLink = rs.getString(5);
                String available = rs.getString(6);
                String category = rs.getString(7);
                products.add(new Product(id,name, brand, imgLink, available, category, price));
            }    
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public ArrayList<String> getCategories(){
        ArrayList<String> categories = new ArrayList<>();
        try {
            String sql = "select DISTINCT category from products";
            rs = stm.executeQuery(sql);
            categories.add("all");
            while(rs.next()){
                String category = rs.getString(1);
                categories.add(category);
            }    
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public ArrayList<Product> getProductsSearching(String input) {
        ArrayList<Product> products = new ArrayList<>();
        try {
                String sql = "select * from products where name like '%" + input +"%'";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String brand = rs.getString(3);
                double price = rs.getDouble(4);
                String imgLink = rs.getString(5);
                String available = rs.getString(6);
                String category = rs.getString(7);
                products.add(new Product(id, name, brand, imgLink, available, category, price));
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public ArrayList<Product> getProductsSearchingByPrice(double input) {
        ArrayList<Product> products = new ArrayList<>();
        try {
                String sql = "select * from products where price  <= " + input;
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String brand = rs.getString(3);
                double price = rs.getDouble(4);
                String imgLink = rs.getString(5);
                String available = rs.getString(6);
                String category = rs.getString(7);
                products.add(new Product(id, name, brand, imgLink, available, category, price));
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public ArrayList<Product> getProductsPagination(int from, int to ) {
        ArrayList<Product> products = new ArrayList<>();
        try {
                String sql = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY id) RN FROM products WHERE RN BETWEEN " + from + " AND " + to +" ORDER BY id";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String brand = rs.getString(3);
                double price = rs.getDouble(4);
                String imgLink = rs.getString(5);
                String available = rs.getString(6);
                String category = rs.getString(7);
                products.add(new Product(id, name, brand, imgLink, available, category, price));
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public Product getProductById(int id){
      try {
                String sql = "SELECT * FROM products WHERE id = " + id;
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString(2);
                String brand = rs.getString(3);
                double price = rs.getDouble(4);
                String imgLink = rs.getString(5);
                String available = rs.getString(6);
                String category = rs.getString(7);
                return new Product(id, name, brand, imgLink, available, category, price);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
}
