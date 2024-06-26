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

    public FuturesTrade(TradeType tradeType, BuySell buySell, String tradeDate, CommodityType commodityType, Counterparty counterparty, double price, int quantity, double discountRate, int daysToSettlement) {
        super(tradeType, buySell, tradeDate, commodityType, counterparty);
        this.price = price;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.daysToSettlement = daysToSettlement;
    }
    
    public FuturesTrade(int id, TradeType tradeType, BuySell buySell, String tradeDate, CommodityType commodityType, Counterparty counterparty, double price, int quantity, double discountRate, int daysToSettlement) {
        super(id, tradeType, buySell, tradeDate, commodityType, counterparty);
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
    
    @Override
    public String toString() {
        return "FuturesTrade{" +
                "id=" + this.getId() +
                ", tradeType='" + this.getTradeType() + '\'' +
                ", tradeDate='" + this.getTradeDate() + '\'' +
                ", commodityType='" + this.getCommodityType() + '\'' +
                ", counterparty='" + this.getCounterparty().getName() + '\'' +
                ", quantity=" + this.quantity +
                ", price=" + this.price +
                ", discountRate=" + this.discountRate +
                ", daysToSettlement=" + this.daysToSettlement +
                '}';
    }
}
