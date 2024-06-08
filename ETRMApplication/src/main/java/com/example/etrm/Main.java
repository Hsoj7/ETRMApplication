package com.example.etrm;

import java.text.NumberFormat;
import java.util.List;

// to do:
// update JUnits for each class
// make a class for insert fake data
public class Main {

	public static void main(String[] args) {
		System.out.println("Establishing Connection...");
		TradeService ts = new TradeService();
		System.out.println("Connected.");
//		ts.clearAllTrades();
		
//		TESTING DATABASE POPULATION
		DataPopulationService dps = new DataPopulationService();
		dps.populateDatabase(50, 50);
		
		
//		TESTING VALUATIONSERVICE
//		ValuationService vs = new ValuationService();
//		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
//
//		SpotTrade st = ts.getRecentSpotTrade();
//		System.out.println("Spot contract: " + st.toString());
//		double spotValue = vs.valueSpotTrade(st);
//        String formatted = currencyFormatter.format(spotValue);
//		System.out.println("Spot value: " + formatted);
//		
//		FuturesTrade ft = ts.getRecentFuturesTrade();
//		System.out.println("Futures contract: " + ft.toString());
//		double futuresValue = vs.valueFuturesTrade(ft);
//		formatted = currencyFormatter.format(futuresValue);
//		System.out.println("Futures value: " + formatted);
		

//		TESTING DELETE works for both spot and futures
//		ts.deleteTrade(6);
		
//		TESTING GET only spot trades and futures trades
//		SpotTrade spot = ts.getSpotTrade(1);
//		System.out.println(spot.toString());
//		spot.setTradeDate("2023-06-06");
//		ts.updateTrade(spot);
//		
//		FuturesTrade future = ts.getFuturesTrade(2);
//		System.out.println(future.toString());
//		future.setTradeDate("2023-06-06");
//		ts.updateTrade(future);
		
		
		
//		TESTING GET ALL TRADES for spot and futures
//		List<Trade> trades = ts.getAllTrades();
//        System.out.println("All Trades:");
//        for(Trade trade : trades) {
//        	System.out.println(trade.toString());
//        }
//        
//        List<SpotTrade> spots = ts.getAllSpotTrades();
//        System.out.println("All Spot Trades:");
//        for(SpotTrade spot : spots) {
//        	System.out.println(spot.toString());
//        }
//        
//        List<FuturesTrade> futures = ts.getAllFuturesTrades();
//        System.out.println("All Futures Trades:");
//        for(FuturesTrade future : futures) {
//        	System.out.println(future.toString());
//        }
		
		
//		TESTING GET TRADES for spot and futures
//		SpotTrade spot = ts.getSpotTrade(1);
//		System.out.println(spot.toString());
//		
//		FuturesTrade fut = ts.getFuturesTrade(5);
//		System.out.println(spot.toString());
		
		
//		TESTING SAVE for spot and futures
//        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, "2023-06-05", "Oil", "Counterparty A", 72.23, 325);
//        ts.saveTrade(spotTrade);
//        FuturesTrade futuresTrade = new FuturesTrade(TradeType.FUTURES, "2023-06-05", "Petroleum", "Counterparty B", 80.0, 750, 0.05, 30);
//        ts.saveTrade(futuresTrade);


						
		ts.close();
		System.out.println("Program terminated");
	}
}
