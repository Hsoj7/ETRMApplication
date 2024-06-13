package com.example.etrm;

import jakarta.persistence.*;

/**
 * This class represents a parent Trade entity with the common attributes
 */
@Entity
@Table(name = "trades")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Trade {
	
	public enum BuySell {
        BUY,
        SELL
    }
	
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
    private String commodityTypeName;
	
	@Transient
    private CommodityType commodityType;
	
	@ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "buy_sell")
    private BuySell buySell;
    
	/**
     * Empty constructor needed for SessionFactory class
     * 
     */
	public Trade(){
		
	}
	
	/**
     * Used to construct a Trade object without an id field - used for creating new trades from the command line
     */
	public Trade(TradeType tradeType, BuySell buySell, String tradeDate, CommodityType commodityType, Counterparty counterparty) {
        this.tradeType = tradeType;
        this.buySell = buySell;
        this.tradeDate = tradeDate;
        this.setCommodityType(commodityType); // Update setter usage
        this.counterparty = counterparty;
    }
	
	/**
     * Used to construct a Trade object with an id field - used for reading from the db
     */
	public Trade(int id, TradeType tradeType, BuySell buySell, String tradeDate, CommodityType commodityType, Counterparty counterparty) {
        this.id = id;
		this.tradeType = tradeType;
        this.buySell = buySell;
        this.tradeDate = tradeDate;
        this.setCommodityType(commodityType); // Update setter usage
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
    public void setCommodityType(CommodityType commodity){
    	this.commodityType = commodity;
        this.commodityTypeName = commodity.getName(); // Set the name for database storage
    }
    
    /**
     * Sets a new counterparty
     */
    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }
    
    /**
     * Sets a new trade type
     */
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }
    
    /**
     * Sets a new BuySell
     */
    public void setBuySell(BuySell buySell){
    	this.buySell = buySell;
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
     * Gets the BuySell
     */
    public BuySell getBuySell(){
    	return buySell;
    }

    /**
     * Gets the commodity type
     */
    public CommodityType getCommodityType() {
        if (commodityType == null && commodityTypeName != null) {
            commodityType = CommodityType.getCommodityTypeByName(commodityTypeName); // Lazy load if needed
        }
        return commodityType;
    }

    /**
     * Gets the counterparty
     */
    public Counterparty getCounterparty() {
        return counterparty;
    }
    
    /**
     * Gets the TradeType
     */
    public TradeType getTradeType() {
        return tradeType;
    }
    
    // Abstract methods to be implemented by subclasses
    public abstract int getQuantity();
    public abstract double getPrice();

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", tradeType='" + tradeType + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", commodityType='" + commodityTypeName + '\'' +
                ", counterparty='" + counterparty.getName() + '\'' +
                '}';
    }
}
