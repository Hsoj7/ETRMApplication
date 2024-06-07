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

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", tradeType='" + tradeType + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", commodityType='" + commodityType + '\'' +
                ", counterparty='" + counterparty + '\'' +
                '}';
    }
}
