package com.example.etrm;

public class ValuationService {

    /**
     * Values a SpotTrade using the formula: Value = Price * Quantity
     * @param spotTrade The SpotTrade to value
     * @return The calculated value of the SpotTrade
     */
    public double valueSpotTrade(SpotTrade spotTrade) {
        double price = spotTrade.getPrice();
        double quantity = spotTrade.getQuantity();
        return price * quantity;
    }

    /**
     * Values a FuturesTrade using the formula: Present Value = Future Price * Quantity / (1 + Discount Rate)^(Days to Settlement / 365)
     * @param futuresTrade The FuturesTrade to value
     * @return The calculated present value of the FuturesTrade
     */
    public double valueFuturesTrade(FuturesTrade futuresTrade) {
        double futurePrice = futuresTrade.getPrice();
        double quantity = futuresTrade.getQuantity();
        double discountRate = futuresTrade.getDiscountRate();
        int daysToSettlement = futuresTrade.getDaysToSettlement();
        
        return futurePrice * quantity / Math.pow(1 + discountRate, daysToSettlement / 365.0);
    }
}



