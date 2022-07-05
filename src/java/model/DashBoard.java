/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HHPC
 */
public class DashBoard {
    private int userCount, historyCount;
    private double totalCost;
    private Item bestSale;

    public DashBoard(int userCount, int historyCount, double totalCost, Item bestSale) {
        this.userCount = userCount;
        this.historyCount = historyCount;
        this.totalCost = totalCost;
        this.bestSale = bestSale;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getHistoryCount() {
        return historyCount;
    }

    public void setHistoryCount(int historyCount) {
        this.historyCount = historyCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Item getBestSale() {
        return bestSale;
    }

    public void setBestSale(Item bestSale) {
        this.bestSale = bestSale;
    }

    @Override
    public String toString() {
        return "DashBoard{" + "userCount=" + userCount + ", historyCount=" + historyCount + ", totalCost=" + totalCost + ", bestSale=" + bestSale + '}';
    }
    
    
}
