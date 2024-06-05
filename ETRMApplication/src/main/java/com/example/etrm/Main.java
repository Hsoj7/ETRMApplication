package com.example.etrm;

import java.util.List;


// Next add JUnit testing
public class Main {

	public static void main(String[] args) {
		System.out.println("Establishing Connection...");
		TradeService ts = new TradeService();
		System.out.println("Connected.");
		
		
//		TESTING saveTrade()
		Trade trade2 = new Trade("2024-06-05", "Gas", 671, 72.10, "My Company", TradeType.SPOT, 0, 0);
		ts.saveTrade(trade2);
		Trade retrieved = ts.getMostRecentTrade();
		System.out.println("Trade2:");
		System.out.println(retrieved.toString());
		
		

//		TESTING getAllTrades()
//		List<Trade> trades = ts.getAllTrades();
//		
//		int startIndex = Math.max(0, trades.size() - 10);
//        List<Trade> lastTen = trades.subList(startIndex, trades.size());
//        
//        for(Trade trade : lastTen) {
//        	System.out.println(trade);
//        }
		
		
//		TESTING deleteTrade()
//		int tradeid = 6;
//		ts.deleteTrade(tradeid);
		
		
//		TESTING updateTrade()
//		Trade trade = ts.getTrade(186);
//		trade.setCommodityType("Electricity");
//		ts.updateTrade(trade);
		

//		TESTING getTrade()
//		int tradeid = 26;
//		Trade trade = ts.getTrade(tradeid);
//	
//		System.out.println("Trade " + tradeid + ":");
//		System.out.println(trade.toString());
		
		

				
		ts.close();
		System.out.println("Program terminated");
	}
}
