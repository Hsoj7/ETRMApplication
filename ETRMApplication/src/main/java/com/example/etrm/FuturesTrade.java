package com.example.etrm;

import jakarta.persistence.*;

@Entity
@Table(name = "futures_trades")
@PrimaryKeyJoinColumn(name = "id")
public class FuturesTrade extends Trade {
    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "discount_rate")
    private double discountRate;

    @Column(name = "days_to_settlement")
    private int daysToSettlement;

    // Constructors, getters, setters, etc.

    public FuturesTrade() {
    	
    }

    public FuturesTrade(TradeType tradeType, String tradeDate, String commodityType, String counterparty, double price, int quantity, double discountRate, int daysToSettlement) {
        super(tradeType, tradeDate, commodityType, counterparty);
        this.price = price;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.daysToSettlement = daysToSettlement;
    }
    
    public FuturesTrade(int id, TradeType tradeType, String tradeDate, String commodityType, String counterparty, double price, int quantity, double discountRate, int daysToSettlement) {
        super(id, tradeType, tradeDate, commodityType, counterparty);
        this.price = price;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.daysToSettlement = daysToSettlement;
    }

    // Getters and setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getDaysToSettlement() {
        return daysToSettlement;
    }

    public void setDaysToSettlement(int daysToSettlement) {
        this.daysToSettlement = daysToSettlement;
    }
}
