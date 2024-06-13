package com.example.etrm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ReportService {
	// Hibernate's implementation for object mappings
    private SessionFactory sessionFactory;

    /**
     * Constructs a TradeService object with the default SessionFactory
     */
    public ReportService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * Constructs a TradeService object with a specified SessionFactory
     * Give the option to pass a separate hibernate config file for testing - needed to accomodate H2 temp database
     * @param sessionFactory The SessionFactory to be used by this TradeService
     */
    public ReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	/**
     * Prints a summary of the positions in each commodity to the console.
     */
    public void printPositionSummaries() {
        // Fetch all trades from the database
        try (Session session = sessionFactory.openSession()) {
            List<Trade> allTrades = session.createQuery("from Trade", Trade.class).getResultList();

            // Map to store cumulative quantity, price, and value for each commodity type
            Map<CommodityType, PositionSummary> positionSummaries = new HashMap<>();

            // Calculate outstanding positions
            for (Trade trade : allTrades) {
                CommodityType commodityType = trade.getCommodityType();
                PositionSummary summary = positionSummaries.getOrDefault(commodityType, new PositionSummary());
                
                double tradeValue = trade.getQuantity() * trade.getPrice();
                double tradeQuantity = trade.getQuantity();
                
                // Update the summary based on buy/sell
                if (trade.getBuySell() == Trade.BuySell.BUY) {
                    summary.totalQuantity += tradeQuantity;
                    summary.totalValue += tradeValue;
                } else {
                    summary.totalQuantity -= tradeQuantity;
                    summary.totalValue -= tradeValue;
                }
                
                // Update the average price (considering total quantity for simplicity)
                if (summary.totalQuantity != 0) {
                    summary.averagePrice = summary.totalValue / summary.totalQuantity;
                } else {
                    summary.averagePrice = 0;
                }

                positionSummaries.put(commodityType, summary);
            }

            // Print the positions summary
            System.out.println("Position Summary:");
            for (Map.Entry<CommodityType, PositionSummary> entry : positionSummaries.entrySet()) {
                CommodityType commodityType = entry.getKey();
                PositionSummary summary = entry.getValue();
                System.out.println("Commodity: " + commodityType.getName() + 
                                   ", Quantity: " + summary.totalQuantity + 
                                   ", Average Price: " + summary.averagePrice + 
                                   ", Value: " + summary.totalValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Generates and prints a profit and loss report for each commodity.
     * 
     * ... FIX LATER average price isn't being calculated correctly.
     */
    public void generateProfitAndLossReport() {
        // Fetch all trades from the database
        try (Session session = sessionFactory.openSession()) {
            List<Trade> allTrades = session.createQuery("from Trade", Trade.class).getResultList();

            // Separate lists for buy and sell trades
            List<Trade> buyTrades = new ArrayList<>();
            List<Trade> sellTrades = new ArrayList<>();
            buyTrades.addAll(allTrades); // Start with all trades as buys

            // Map to keep track of total P&L
            Map<CommodityType, Double> profitAndLoss = new HashMap<>();

            // Iterate through all trades and calculate P&L
            for (Trade trade : allTrades) {
                if (trade.getBuySell() == Trade.BuySell.SELL) {
                    sellTrades.add(trade);
                }
            }

            // Calculate P&L based on sell trades
            for (Trade sellTrade : sellTrades) {
                CommodityType commodityType = sellTrade.getCommodityType();
                double sellQuantity = sellTrade.getQuantity();
                double sellPrice = sellTrade.getPrice();
                double remainingQuantity = sellQuantity;

                // Iterate through buy trades for this commodity
                Iterator<Trade> buyTradeIterator = buyTrades.iterator();
                while (buyTradeIterator.hasNext()) {
                    Trade buyTrade = buyTradeIterator.next();
                    if (buyTrade.getCommodityType() == commodityType) {
                        int buyQuantity = buyTrade.getQuantity();
                        double buyPrice = buyTrade.getPrice();
                        int availableToSell = (int) Math.min(remainingQuantity, buyQuantity);

                        // Calculate P&L for this trade
                        double tradePnL = availableToSell * (sellPrice - buyPrice);
                        profitAndLoss.put(commodityType, profitAndLoss.getOrDefault(commodityType, 0.0) + tradePnL);

                        // Update remaining quantity
                        remainingQuantity -= availableToSell;

                        // Update buy trade quantity or remove if fully sold
                        if (availableToSell >= buyQuantity) {
                            buyTradeIterator.remove();
                        } else {
                            buyTrade.setQuantity(buyQuantity - availableToSell);
                        }

                        // Exit the loop if remaining quantity is 0
                        if (remainingQuantity == 0) {
                            break;
                        }
                    }
                }
            }

            // Print the P&L report
            System.out.println("Profit and Loss Report:");
            for (Map.Entry<CommodityType, Double> entry : profitAndLoss.entrySet()) {
                System.out.println("Commodity: " + entry.getKey().getName() + ", P&L: " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    
    /**
     * Closes the SessionFactory
     */
    public void close() {
        sessionFactory.close();
    }
    
 // Custom class to hold position summary details
    class PositionSummary {
        double totalQuantity = 0;
        double averagePrice = 0;
        double totalValue = 0;
    }
}
