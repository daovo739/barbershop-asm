/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.ProductsDAO;
import java.sql.SQLException;

/**
 *
 * @author HHPC
 */
public class Item {
    private int userId, quantity, productId;
    Product product;
    private double totalCost;
    public Item(int userId, int quantity, int productId) throws SQLException {
        ProductsDAO productsDAO = new ProductsDAO();
        this.userId = userId;
        this.quantity = quantity;
        this.productId = productId;
        this.product = productsDAO.getProductById(productId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalCost() {
        return this.getProduct().getPrice() * this.getQuantity();
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    
}
