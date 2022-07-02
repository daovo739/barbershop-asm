/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author HHPC
 */
public class Cart {

    private ArrayList<Item> cart;
    private double totalCostCart;

      public Cart() {
        cart = new ArrayList<>();
    }

    public double getTotalCostCart() {
        totalCostCart = 0;
        for (Item item : cart) {
            totalCostCart += item.getTotalCost();
        }
        return formatPrice(totalCostCart);
    }

    public void settotalCostCartt(double totalCostCart) {
        this.totalCostCart = totalCostCart;
    }


    public ArrayList<Item> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Item> cart) {
        this.cart = cart;
    }

    public double formatPrice(double price){
        DecimalFormat df=new DecimalFormat("#.##");
        return Double.parseDouble(df.format(price));
    }
}
