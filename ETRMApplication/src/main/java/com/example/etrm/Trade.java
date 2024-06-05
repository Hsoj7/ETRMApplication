package com.example.etrm;

import jakarta.persistence.*;

/**
 * This class represents a parent Trade entity with the common attributes
 */
@Entity
@Table(name = "trades")
@Inheritance(strategy = InheritanceType.JOINED)
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "trade_type")
    private TradeType tradeType;
	
	@Column(name = "trade_date")
    private String tradeDate;
	
	@Column(name = "commodity_type")
    private String commodityType;
	
	@Column(name = "counterparty")
    private String counterparty;
    
    
	/**
     * Empty constructor needed for SessionFactory class
     * 
     */
	public Trade(){
		
	}
	
	/**
     * Used to construct a Trade object without an id field - used for creating new trades from the command line
     */
	public Trade(TradeType tradeType, String tradeDate, String commodityType, String counterparty) {
        this.tradeType = tradeType;
        this.tradeDate = tradeDate;
        this.commodityType = commodityType;
        this.counterparty = counterparty;
    }
	
	/**
     * Used to construct a Trade object with an id field - used for reading from the db
     */
	public Trade(int id, TradeType tradeType, String tradeDate, String commodityType, String counterparty) {
        this.id = id;
		this.tradeType = tradeType;
        this.tradeDate = tradeDate;
        this.commodityType = commodityType;
        this.counterparty = counterparty;
    }
	
	/**
     * Constructs a Trade object without an id field - used for creating new trades from the command line
     * 
     * @param tradeDate     The date of the trade.
     * @param commodityType The type of commodity traded.
     * @param quantity      The quantity of the trade.
     * @param price         The price per unit of the commodity.
     * @param counterparty  The counterparty involved in the trade.
     */
//	public Trade(String tradeDate, String commodityType, double quantity, double price, String counterparty, TradeType tradeType, double discountRate, int daysToSettlement){
//    	if (tradeDate == null || tradeDate.isEmpty()) {
//            throw new IllegalArgumentException("Trade date cannot be null or empty");
//        }
//        if (commodityType == null || commodityType.isEmpty()) {
//            throw new IllegalArgumentException("Commodity type cannot be null or empty");
//        }
//        if (quantity <= 0) {
//            throw new IllegalArgumentException("Quantity must be greater than zero");
//        }
//        if (price <= 0) {
//            throw new IllegalArgumentException("Price must be greater than zero");
//        }
//
//        this.id = 0;
//        this.tradeDate = tradeDate;
//        this.commodityType = commodityType;
//        this.quantity = quantity;
//        this.price = price;
//        this.counterparty = counterparty;
//        this.tradeType = tradeType;
//        this.discountRate = discountRate;
//        this.daysToSettlement = daysToSettlement;
//    }
	
	/**
     * Constructs a Trade object including the ID field - used for reading from existing trades
     * 
     * @param id            The identifier of the trade.
     * @param tradeDate     The date of the trade.
     * @param commodityType The type of commodity traded.
     * @param quantity      The quantity of the trade.
     * @param price         The price per unit of the commodity.
     * @param counterparty  The counterparty involved in the trade.
     */
//    public Trade(int id, String tradeDate, String commodityType, double quantity, double price, String counterparty, TradeType tradeType, double discountRate, int daysToSettlement){
//    	if (tradeDate == null || tradeDate.isEmpty()) {
//            throw new IllegalArgumentException("Trade date cannot be null or empty");
//        }
//        if (commodityType == null || commodityType.isEmpty()) {
//            throw new IllegalArgumentException("Commodity type cannot be null or empty");
//        }
//        if (quantity <= 0) {
//            throw new IllegalArgumentException("Quantity must be greater than zero");
//        }
//        if (price <= 0) {
//            throw new IllegalArgumentException("Price must be greater than zero");
//        }
//
//        this.id = id;
//        this.tradeDate = tradeDate;
//        this.commodityType = commodityType;
//        this.quantity = quantity;
//        this.price = price;
//        this.counterparty = counterparty;
//        this.tradeType = tradeType;
//        this.discountRate = discountRate;
//        this.daysToSettlement = daysToSettlement;
//    }
    
    /**
     * Sets a new trade date
     */
    public void setTradeDate(String date){
    	this.tradeDate = date;
    }
    
    /**
     * Sets a new commodity type
     */
    public void setCommodityType(String commodity){
    	this.commodityType = commodity;
    }
    
    /**
     * Sets a new counterparty
     */
    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }
    
    /**
     * Sets a new trade type
     */
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }
    
    /**
     * Gets the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the trade date
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * Gets the commodity type
     */
    public String getCommodityType() {
        return commodityType;
    }

    /**
     * Gets the counterparty
     */
    public String getCounterparty() {
        return counterparty;
    }
    
    /**
     * Gets the TradeType
     */
    public TradeType getTradeType() {
        return tradeType;
    }



    
    
    
//    /**
//     * Returns the trade summary as a formatted string.
//     * 
//     * @return String representing the trade summary.
//     */
//    public String tradeSummary() {
//    	StringBuilder sb = new StringBuilder();
//    	sb.append("id: " + this.id  + "\n");
//    	sb.append("Trade Date: " + this.tradeDate  + "\n");
//    	sb.append("Commodity Type: " + this.commodityType  + "\n");
//    	sb.append("Quantity: " + this.quantity  + "\n");
//    	sb.append("Price: " + this.price  + "\n");
//    	sb.append("Counterparty: " + this.counterparty  + "\n");
//
//    	String summary = sb.toString();
//    	
//    	return summary;
//    }
//    
//    @Override
//    public String toString() {
//        return "Trade{" +
//                "id=" + id +
//                ", tradeDate='" + tradeDate + '\'' +
//                ", commodityType='" + commodityType + '\'' +
//                ", quantity=" + quantity +
//                ", price=" + price +
//                ", counterparty='" + counterparty + '\'' +
//                '}';
//    }
}
