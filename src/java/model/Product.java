/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Admin
 */
public class Product {
    private int id;
    private String name, brand, imgLink, availabe, category;
    private double price;

    public Product(int id, String name, String brand, String imgLink, String availabe, String category, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.imgLink = imgLink;
        this.availabe = availabe;
        this.category = category;
        this.price = price;
    }

      public Product(String name, String brand, String imgLink, String availabe, String category, double price) {
        this.name = name;
        this.brand = brand;
        this.imgLink = imgLink;
        this.availabe = availabe;
        this.category = category;
        this.price = price;
    }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getAvailabe() {
        return availabe;
    }

    public void setAvailabe(String availabe) {
        this.availabe = availabe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return Math.round(price*100.0)/100.0;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", brand=" + brand + ", imgLink=" + imgLink + ", availabe=" + availabe + ", category=" + category + ", price=" + price + '}';
    }

    
    
}
