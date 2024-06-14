package com.example.etrm;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.example.etrm.Trade.BuySell;

// to do:
// implement present value for futures trades in verifyTrade
// update JUnits for each class
 
public class Main {
	
	/*
	 * Function to clear the DB and populate with new trades from Jan 1, 2023 onwards
	 */
	public void populateDB(TradeService ts) {
		ts.clearAllTrades();
		LocalDate startDate = LocalDate.of(2023, 01, 01);
		DataPopulationService dps = new DataPopulationService(ts);
		dps.populateDatabase(startDate, 10);
	}
	
	/*
	 * Driver function to call ReportService and print position summaries & P&L report
	 */
	public void printTradeReport() {
		System.out.println("Establishing Report Service...");
		ReportService rs = new ReportService();
		System.out.println("Connected.");

		rs.printPositionSummaries();
		rs.generateProfitAndLossReport();
		
		rs.close();
	}
	
	/*
	 * Testing function to save single trades
	 */
	public void testSaveTrade(TradeService ts) {
		Random random = new Random();
		CounterpartyService cs = new CounterpartyService();
		Counterparty counterparty = cs.getCounterparty(random.nextInt(7) + 1);
		SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, BuySell.SELL, LocalDate.now().toString(), CommodityType.CRUDE_OIL, counterparty, 83.0, 35);
        try {
            ts.verifyTrade(spotTrade);
            ts.saveTrade(spotTrade);
        } catch (IllegalArgumentException e) {
            System.out.println("Trade failed: " + e.getMessage());
        }
	}
	
	/*
	 * Testing function to test saving a trade with numbers that may break trade limits
	 */
	public void testTradeLimit(TradeService ts) {
		CounterpartyService cs = new CounterpartyService();
		Counterparty counterparty = cs.getCounterparty(1);
		
		SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, BuySell.BUY, LocalDate.now().toString(), CommodityType.ELECTRICITY, counterparty, 500.0, 100);
		
        try {
            ts.verifyTrade(spotTrade);
            ts.saveTrade(spotTrade);
        } catch (IllegalArgumentException e) {
            System.out.println("Trade failed: " + e.getMessage());
        }
	}

	public static void main(String[] args) {
		
		System.out.println("Establishing Trade Service...");
		TradeService ts = new TradeService();
		System.out.println("Connected.");

		Main main = new Main();
		
//		populate DB with new values
//		main.populateDB(ts);
		
//		print the trade report summaries and P&L
		main.printTradeReport();
		

		ts.close();
		
		System.out.println("Program terminated");
	}
}
