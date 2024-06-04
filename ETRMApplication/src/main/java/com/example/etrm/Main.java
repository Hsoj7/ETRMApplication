package com.example.etrm;

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		TradeService ts = new TradeService();
		
		Trade trade = ts.getTrade(26);
		
		System.out.println(trade.toString());
		
		
//		Trade trade = new Trade(1, "2024-05-29", "Gas", 526.32, 30.68, "NuVista");

		
//		String summary = trade.tradeSummary();
//		System.out.println(summary);
//		
//		String toString = trade.toString();
//		System.out.println(toString);
		
//		ArrayList<Trade> trades = new ArrayList<Trade>();
//		
//		try {
//            Connection connection = DatabaseUtil.getConnection();
//
//		    Statement statement = connection.createStatement();
//		    ResultSet resultSet = statement.executeQuery("SELECT * FROM trades");
//
//		    while (resultSet.next()) {
//		    	Trade trade = new Trade(resultSet.getInt("id"), resultSet.getString("trade_date"), resultSet.getString("commodity_type"), resultSet.getDouble("amount"), resultSet.getDouble("price"), resultSet.getString("counterparty"));
//		     	trades.add(trade);
//		    }
//
//		    // Close resources
//		    resultSet.close();
//		    statement.close();
//		    connection.close();
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//		
//		int i = 0;
//		for(Trade trade : trades) {
//			System.out.println(trade.tradeSummary());
//		}
	}
}
