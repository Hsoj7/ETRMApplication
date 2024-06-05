package com.example.etrm;


public class ValuationService {

    public double valueTrade(Trade trade) {
        switch (trade.getTradeType()) {
            case SPOT:
                return valueSpotTrade(trade);
            case FUTURES:
                return valueFuturesTrade(trade);
            default:
                throw new IllegalArgumentException("Unsupported trade type: " + trade.getTradeType());
        }
    }

    private double valueSpotTrade(Trade trade) {
        return trade.getPrice() * trade.getQuantity();
    }

    private double valueFuturesTrade(Trade trade) {
        if (trade.getFuturePrice() == null || trade.getDiscountRate() == null || trade.getDaysToSettlement() == null) {
            throw new IllegalArgumentException("Missing required fields for futures trade valuation");
        }
        return trade.getFuturePrice() * trade.getQuantity() / 
               Math.pow(1 + trade.getDiscountRate(), trade.getDaysToSettlement() / 365.0);
    }
}


