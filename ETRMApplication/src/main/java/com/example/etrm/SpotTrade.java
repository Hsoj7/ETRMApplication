package com.example.etrm;

import jakarta.persistence.*;

@Entity
@Table(name = "spot_trades")
@PrimaryKeyJoinColumn(name = "id")
public class SpotTrade extends Trade {
	
    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    
    public SpotTrade() {
    	
    }

    public SpotTrade(TradeType tradeType, String tradeDate, String commodityType, String counterparty, double price, int quantity) {
        super(tradeType, tradeDate, commodityType, counterparty);
        this.price = price;
        this.quantity = quantity;
    }

    public SpotTrade(int id, TradeType tradeType, String tradeDate, String commodityType, String counterparty, double price, int quantity) {
        super(id, tradeType, tradeDate, commodityType, counterparty);
        this.price = price;
        this.quantity = quantity;
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
    
  @Override
  public String toString() {
      return "SpotTrade{" +
              "id=" + this.getId() +
              ", tradeType='" + this.getTradeType() + '\'' +
              ", tradeDate='" + this.getTradeDate() + '\'' +
              ", commodityType='" + this.getCommodityType() + '\'' +
              ", counterparty='" + this.getCounterparty() + '\'' +
              ", quantity=" + this.quantity +
              ", price=" + this.price +
              '}';
  }
}

