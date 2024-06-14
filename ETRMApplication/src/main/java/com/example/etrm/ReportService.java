package com.example.etrm;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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

            // Map to store outstanding quantities and values for each commodity type
            Map<CommodityType, PositionSummary> positionSummaries = new HashMap<>();

            // Calculate outstanding quantities and values
            for (Trade trade : allTrades) {
                CommodityType commodityType = trade.getCommodityType();
                PositionSummary summary = positionSummaries.getOrDefault(commodityType, new PositionSummary());

                double tradeQuantity = trade.getQuantity();
                double tradePrice = trade.getPrice();
                double tradeValue = tradeQuantity * tradePrice;

                // Update the summary based on buy/sell
                if (trade.getBuySell() == Trade.BuySell.BUY) {
                    summary.totalQuantity += tradeQuantity;
                    summary.totalValue += tradeValue;
                } else {
                    summary.totalQuantity -= tradeQuantity;
                    summary.totalValue -= tradeValue;
                }

                positionSummaries.put(commodityType, summary);
            }

            // Print the positions summary
            System.out.println("\nPosition Summary:");
            for (Map.Entry<CommodityType, PositionSummary> entry : positionSummaries.entrySet()) {
                CommodityType commodityType = entry.getKey();
                PositionSummary summary = entry.getValue();
                System.out.println("Commodity: " + commodityType.getName() +
                                   ", Outstanding Quantity: " + summary.totalQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Custom class to hold position summary details
    static class PositionSummary {
        double totalQuantity = 0;
        double totalValue = 0;
    }
    
    /**
     * Generates and prints a profit and loss report for each commodity.
     * 
     */
    public void generateProfitAndLossReport() {
        // fetch all trades from the database
        try (Session session = sessionFactory.openSession()) {
            List<Trade> allTrades = session.createQuery("from Trade", Trade.class).getResultList();

            // map to store the FIFO queues of buy trades for each commodity
            Map<CommodityType, LinkedList<Trade>> buyQueues = new HashMap<>();
            // map to store cumulative P&L for each commodity
            Map<CommodityType, Double> profitAndLoss = new HashMap<>();

            // initialize the maps
            for (Trade trade : allTrades) {
            	// initialize buyQueues with a linked list for each commodity type
                buyQueues.putIfAbsent(trade.getCommodityType(), new LinkedList<Trade>());
                // initialize profitAndLoss commodity type mapings with 0s
                profitAndLoss.putIfAbsent(trade.getCommodityType(), 0.0);
            }

            // process each trade
            for (Trade trade : allTrades) {
                CommodityType commodityType = trade.getCommodityType();
                LinkedList<Trade> queue = buyQueues.get(commodityType);

                if (trade.getBuySell() == Trade.BuySell.BUY) {
                    // add the buy trade to the queue
                    queue.add(trade);
                } else {
                    // process the sell trade and calculate P&L
                    int sellQuantity = trade.getQuantity();
                    double sellPrice = trade.getPrice();

                    // iterate through the buy queue starting with 
                    while (sellQuantity > 0 && !queue.isEmpty()) {
                        Trade buyTrade = queue.peek();
                        int buyQuantity = buyTrade.getQuantity();
                        double buyPrice = buyTrade.getPrice();

                        // case where sell is less than the outstanding position
                        if (buyQuantity <= sellQuantity) {
                            // calculate and remove buy trade
                            double buyValue = buyQuantity * buyPrice;
                            double pnl = (sellPrice - buyPrice) * buyQuantity;
                            profitAndLoss.put(commodityType, profitAndLoss.get(commodityType) + pnl);

                            sellQuantity -= buyQuantity;
                            queue.poll();
                        // case where sell is more than the outstanding position
                        } else {
                            // calculate and adjust remaining buy trade
                            double buyValue = sellQuantity * buyPrice;
                            double pnl = (sellPrice - buyPrice) * sellQuantity;
                            profitAndLoss.put(commodityType, profitAndLoss.get(commodityType) + pnl);

                            buyTrade.setQuantity(buyQuantity - sellQuantity);
                            sellQuantity = 0;
                        }
                    }
                }
            }

            // print the P&L report
            System.out.println("");
            System.out.println("Profit and Loss Report:");
            NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
            for (Map.Entry<CommodityType, Double> entry : profitAndLoss.entrySet()) {
                System.out.println("Commodity: " + entry.getKey().getName() + ", P&L: " + usdFormat.format(entry.getValue()));
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
    
}
