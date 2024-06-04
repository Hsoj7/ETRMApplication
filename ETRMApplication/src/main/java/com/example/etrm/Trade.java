package com.example.etrm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

/**
 * This class represents a Trade entity with its attributes.
 * It provides methods to manage trade information.
 */
@Entity
@Table(name = "trades")
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "trade_date")
    private String tradeDate;
	
	@Column(name = "commodity_type")
    private String commodityType;
	
	@Column(name = "amount")
    private double quantity;
	
	@Column(name = "price")
    private double price;
	
	@Column(name = "counterparty")
    private String counterparty;
    
    /**
     * Constructs a Trade object with the specified attributes.
     * 
     * @param id            The identifier of the trade.
     * @param tradeDate     The date of the trade.
     * @param commodityType The type of commodity traded.
     * @param quantity      The quantity of the trade.
     * @param price         The price per unit of the commodity.
     * @param counterparty  The counterparty involved in the trade.
     */
	
	public Trade(){
		
	}
	
	
    public Trade(int id, String tradeDate, String commodityType, double quantity, double price, String counterparty){
    	if (tradeDate == null || tradeDate.isEmpty()) {
            throw new IllegalArgumentException("Trade date cannot be null or empty");
        }
        if (commodityType == null || commodityType.isEmpty()) {
            throw new IllegalArgumentException("Commodity type cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        this.id = id;
        this.tradeDate = tradeDate;
        this.commodityType = commodityType;
        this.quantity = quantity;
        this.price = price;
        this.counterparty = counterparty;
    }
    
    /**
     * Returns the trade summary as a formatted string.
     * 
     * @return String representing the trade summary.
     */
    public String tradeSummary() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("id: " + this.id  + "\n");
    	sb.append("Trade Date: " + this.tradeDate  + "\n");
    	sb.append("Commodity Type: " + this.commodityType  + "\n");
    	sb.append("Quantity: " + this.quantity  + "\n");
    	sb.append("Price: " + this.price  + "\n");
    	sb.append("Counterparty: " + this.counterparty  + "\n");

    	String summary = sb.toString();
    	
    	return summary;
    }
    
    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", tradeDate='" + tradeDate + '\'' +
                ", commodityType='" + commodityType + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", counterparty='" + counterparty + '\'' +
                '}';
    }
}
