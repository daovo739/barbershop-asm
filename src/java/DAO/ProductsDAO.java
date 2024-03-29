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
    
     public ArrayList<Product> getAllProducts() {
        try {
            ArrayList<Product> products = new ArrayList<>();
            String sql = "Select * from products";
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
     
    public ArrayList<Product> getProducts(String categoryInput, String sortInput) {
        ArrayList<Product> products = new ArrayList<>();
        String sql;
        try {
            if (sortInput.isEmpty()) {
                if (categoryInput.equals("all")) {
                    sql = "Select * from products";
                } else {
                    sql = "Select * from products where category = '" + categoryInput + "'";
                }
            }else{
                if (categoryInput.equals("all")) {
                    sql = "Select * from products order by price " + sortInput ;
                } else {
                    sql = "Select * from products where category = '" + categoryInput + "' order by price " + sortInput;
                }
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
     
     public Product getProductById(int id){
      try {
                String sql = "SELECT * FROM products WHERE product_id = " + id;
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

    public double getPriceByName(String name) {
        try {
            String sql = "select price from products where name  = " + name;
            rs = stm.executeQuery(sql);
            rs.next();
            return rs.getDouble(1);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     
    public boolean updateProduct(Product product){
        try {
            String sql = "update products set name = ? , brand = ?, price = ?, imgLink = ?, category = ? where product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImgLink());
            ps.setString(5, product.getCategory());
            ps.setInt(6, product.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean createProduct(Product product){
        try {
            String sql = "insert into products values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImgLink());
            ps.setString(5, product.getAvailabe());
            ps.setString(6, product.getCategory());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteProduct(int id){
        try {
            String sql = "delete from products where product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
