package com.example.etrm;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class DataPopulationService {

    private CounterpartyService cs;
    private List<Counterparty> parties;
    private TradeService ts;
    Random random = new Random();
    
    public DataPopulationService() {
    	cs = new CounterpartyService();
    	parties = cs.getAllCounterparties();
    	ts = new TradeService();
    }
    
    /**
     * Simulates historical trades daily from the startDate onwards. Inserts a random number from 0 to maxTradesPerDay of trades each day.
     * @param startDate	The date to begin inserting trades
     * @param maxTradesPerDay	The max number of possible trades on a given day. A random number from 0 to maxTradesPerDay is selected each day
     */
    public void populateDatabase(LocalDate startDate, int maxTradesPerDay) {
        LocalDate currentDate = startDate;
        LocalDate today = LocalDate.now();

        while (!currentDate.isAfter(today)) {
            int numberOfTrades = random.nextInt(maxTradesPerDay + 1);
            for (int i = 0; i < numberOfTrades; i++) {
                if (random.nextBoolean()) {
                    SpotTrade spotTrade = createRandomSpotTrade(currentDate);
                    ts.saveTrade(spotTrade);
                } else {
                    FuturesTrade futuresTrade = createRandomFuturesTrade(currentDate);
                    ts.saveTrade(futuresTrade);
                }
            }
            currentDate = currentDate.plusDays(1);
        }
    }

    /**
     * Inserts a random number of spot and futures trades with TODAY's date
     */
    public void populateDatabase(int numberOfSpotTrades, int numberOfFuturesTrades) {
        int remainingSpotTrades = numberOfSpotTrades;
        int remainingFuturesTrades = numberOfFuturesTrades;
        LocalDate today = LocalDate.now();

        while (remainingSpotTrades > 0 || remainingFuturesTrades > 0) {
            if (remainingSpotTrades > 0 && remainingFuturesTrades > 0) {
                if (random.nextBoolean()) {
                    SpotTrade spotTrade = createRandomSpotTrade(today);
                    ts.saveTrade(spotTrade);
                    remainingSpotTrades--;
                } else {
                    FuturesTrade futuresTrade = createRandomFuturesTrade(today);
                    ts.saveTrade(futuresTrade);
                    remainingFuturesTrades--;
                }
            } else if (remainingSpotTrades > 0) {
                SpotTrade spotTrade = createRandomSpotTrade(today);
                ts.saveTrade(spotTrade);
                remainingSpotTrades--;
            } else if (remainingFuturesTrades > 0) {
                FuturesTrade futuresTrade = createRandomFuturesTrade(today);
                ts.saveTrade(futuresTrade);
                remainingFuturesTrades--;
            }
        }
    }

    private SpotTrade createRandomSpotTrade(LocalDate day) {
        SpotTrade spotTrade = new SpotTrade();
        spotTrade.setTradeDate(day.toString());
        
        CommodityType[] commodities = CommodityType.VALUES;
        int randomIndex = random.nextInt(commodities.length);
        CommodityType commodityType = commodities[randomIndex];
        spotTrade.setCommodityType(commodityType);

        spotTrade.setBuySell(random.nextBoolean() ? Trade.BuySell.BUY : Trade.BuySell.SELL);
        spotTrade.setCounterparty(parties.get(random.nextInt(parties.size())));
        
//        spotTrade.setQuantity(random.nextInt(200));
//        spotTrade.setPrice(random.nextInt(1000));
     // Calculate random quantity within 20% of targetQuantity
        int targetQuantity = commodityType.getTargetQuantity();
        double quantityVariation = targetQuantity * 0.2; // 20% variation
        int quantity = targetQuantity - (int) (quantityVariation * 2 * random.nextDouble()); // -20% to +20%
        spotTrade.setQuantity(quantity);

        // Calculate random price within 20% of targetUnitPrice
        double targetUnitPrice = commodityType.getTargetUnitPrice();
        double priceVariation = targetUnitPrice * 0.2; // 20% variation
        double price = targetUnitPrice - (priceVariation * 2 * random.nextDouble()); // -20% to +20%
        spotTrade.setPrice(price);
        
        spotTrade.setTradeType(TradeType.SPOT);
        return spotTrade;
    }

    private FuturesTrade createRandomFuturesTrade(LocalDate day) {
        FuturesTrade futuresTrade = new FuturesTrade();
        futuresTrade.setTradeDate(day.toString());
        
        CommodityType[] commodities = CommodityType.VALUES;
        int randomIndex = random.nextInt(commodities.length);
        CommodityType commodityType = commodities[randomIndex];
        futuresTrade.setCommodityType(commodityType);
        
        futuresTrade.setBuySell(random.nextBoolean() ? Trade.BuySell.BUY : Trade.BuySell.SELL);
        futuresTrade.setCounterparty(parties.get(random.nextInt(parties.size())));
        
//        futuresTrade.setPrice(random.nextInt(1000));
//        futuresTrade.setQuantity(random.nextInt(200));
     // Calculate random quantity within 20% of targetQuantity
        int targetQuantity = commodityType.getTargetQuantity();
        double quantityVariation = targetQuantity * 0.2; // 20% variation
        int quantity = targetQuantity - (int) (quantityVariation * 2 * random.nextDouble()); // -20% to +20%
        futuresTrade.setQuantity(quantity);
        
        // Calculate random price within 20% of targetUnitPrice
        double targetUnitPrice = commodityType.getTargetUnitPrice();
        double priceVariation = targetUnitPrice * 0.2; // 20% variation
        double price = targetUnitPrice - (priceVariation * 2 * random.nextDouble()); // -20% to +20%
        futuresTrade.setPrice(price);
        
        futuresTrade.setDiscountRate(random.nextDouble() * 0.1);
        futuresTrade.setDaysToSettlement(random.nextInt(365));
        futuresTrade.setTradeType(TradeType.FUTURES);
        return futuresTrade;
    }
}
