package com.example.etrm;

import java.util.List;


// Next add JUnit testing
public class Main {

	public static void main(String[] args) {
		System.out.println("Establishing Connection...");
		TradeService ts = new TradeService();
		System.out.println("Connected.");

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
//		int tradeid = 182;
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
		
		
//		TESTING saveTrade()
		Trade trade2 = new Trade("2024-05-29", "Gas", 526.32, 30.68, "NuVista");
		System.out.println("Trade2:");
		System.out.println(trade2.toString());
		ts.saveTrade(trade2);
				
		ts.close();
		System.out.println("Program terminated");
	}
}
