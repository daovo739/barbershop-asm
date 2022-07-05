/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import DAO.ProductsDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
/**
 *
 * @author Admin
 */
public class History {

    private int historyId, userId, productId,  quantity;
    private HashMap<Product, Integer> productsList;
    User user;
    private String email, phone, name, city, district, ward, address, deliveryMethod, paymentMethod;
    private double totalCostHistory;
    private String createAt;
    
    public History(int userId, String email, String phone, String name, String city, String district, String ward, String address, String deliveryMethod, String paymentMethod, double totalCostHistory) {
       this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.deliveryMethod = deliveryMethod;
        this.paymentMethod = paymentMethod;
        this.totalCostHistory = totalCostHistory;
        this.productsList = new HashMap<>();
        setCreateAt();
    }

    public History(int historyId, int userId,  Timestamp date, String email, String phone, String name, String city, String district, String ward, String address, String deliveryMethod, String paymentMethod, double totalCostHistory) throws SQLException {
        this.historyId = historyId;
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.deliveryMethod = deliveryMethod;
        this.paymentMethod = paymentMethod;
        this.totalCostHistory = totalCostHistory;
        this.productsList = new HashMap<>();
        setCreateAtTimestamp(date);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    public HashMap<Product, Integer> getProductsList() {
        return productsList;
    }

    public void setProductsList(HashMap<Product, Integer> productsList) {
        this.productsList = productsList;
    }

    
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public double getTotalCostHistory() {
        return formatPrice(totalCostHistory);
    }

    public void setTotalCostHistory(double totalCostHistory) {
        this.totalCostHistory = totalCostHistory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt() {
       
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(new Date());
        this.createAt = date;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreateAtTimestamp(Timestamp date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newBookingDate = formatter.format(date);
        this.createAt = newBookingDate;
    }
    
     public double formatPrice(double price){
        DecimalFormat df=new DecimalFormat("#.##");
        return Double.parseDouble(df.format(price));
    }
    @Override
    public String toString() {
        return "History{" + "historyId=" + historyId + ", userId=" + userId + ", productIdList=" + productsList + ", email=" + email + ", phone=" + phone + ", name=" + name + ", city=" + city + ", district=" + district + ", ward=" + ward + ", address=" + address + ", deliveryMethod=" + deliveryMethod + ", paymentMethod=" + paymentMethod + ", totalCostHistory=" + totalCostHistory + ", createAt=" + createAt + '}';
    }

    
    
   

}
