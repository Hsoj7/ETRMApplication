package com.example.etrm;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.example.etrm.Trade.BuySell;

// to do:
// implement ReportService to calculate trade positions, and P&L on given trades
// implement present value for futures trades in verifyTrade
// update JUnits for each class

 
public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		System.out.println("Establishing Trade Service...");
		TradeService ts = new TradeService();
		System.out.println("Connected.");
//		ts.clearAllTrades();

		
		
		CounterpartyService cs = new CounterpartyService();
		Counterparty counterparty = cs.getCounterparty(random.nextInt(7) + 1);
		SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, BuySell.SELL, LocalDate.now().toString(), CommodityType.CRUDE_OIL, counterparty, 83.0, 35);
        try {
            ts.verifyTrade(spotTrade);
            ts.saveTrade(spotTrade);
        } catch (IllegalArgumentException e) {
            System.out.println("Trade failed: " + e.getMessage());
        }
		
		
		
//		TESTING REPORTING SERVICE
		System.out.println("Establishing Report Service...");
		ReportService rs = new ReportService();
		System.out.println("Connected.");
//		
		rs.printPositionSummaries();
		rs.generateProfitAndLossReport();
		
		
		
//		TESTING POSITION LIMITS
//		CounterpartyService cs = new CounterpartyService();
//		Counterparty counterparty = cs.getCounterparty(1);
//		
//		SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, BuySell.BUY, LocalDate.now().toString(), CommodityType.ELECTRICITY, counterparty, 500.0, 100);
//		
//        try {
//            ts.verifyTrade(spotTrade);
//            ts.saveTrade(spotTrade);
//        } catch (IllegalArgumentException e) {
//            // Trade verification failed, create a new trade and try again
//            System.out.println("Trade failed: " + e.getMessage());
//        }
        
		
//		TESTING DATABASE POPULATION
//		ts.clearAllTrades();
//		LocalDate startDate = LocalDate.of(2023, 01, 01);
//		DataPopulationService dps = new DataPopulationService(ts);
//		dps.populateDatabase(startDate, 10);
//		
//		rs.printPositionSummaries();
		
		
//		TESTING DATABASE POPULATION with 50 spot and 50 futures trades that will use today's date
//		dps.populateDatabase(50, 50);
		
		
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


						
//		ts.close();
		
		rs.close();
		
		System.out.println("Program terminated");
	}
}
