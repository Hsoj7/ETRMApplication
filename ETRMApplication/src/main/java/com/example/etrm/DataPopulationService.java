package com.example.etrm;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class DataPopulationService {

    private CounterpartyService cs;
    private List<Counterparty> parties;
    Random random = new Random();
    
    public DataPopulationService() {
    	cs = new CounterpartyService();
    	parties = cs.getAllCounterparties();
    }

    public void populateDatabase(int numberOfSpotTrades, int numberOfFuturesTrades) {
        int remainingSpotTrades = numberOfSpotTrades;
        int remainingFuturesTrades = numberOfFuturesTrades;

        while (remainingSpotTrades > 0 || remainingFuturesTrades > 0) {
            if (remainingSpotTrades > 0 && remainingFuturesTrades > 0) {
                if (random.nextBoolean()) {
                    SpotTrade spotTrade = createRandomSpotTrade();
                    saveTrade(spotTrade);
                    remainingSpotTrades--;
                } else {
                    FuturesTrade futuresTrade = createRandomFuturesTrade();
                    saveTrade(futuresTrade);
                    remainingFuturesTrades--;
                }
            } else if (remainingSpotTrades > 0) {
                SpotTrade spotTrade = createRandomSpotTrade();
                saveTrade(spotTrade);
                remainingSpotTrades--;
            } else if (remainingFuturesTrades > 0) {
                FuturesTrade futuresTrade = createRandomFuturesTrade();
                saveTrade(futuresTrade);
                remainingFuturesTrades--;
            }
        }
    }

    private SpotTrade createRandomSpotTrade() {
        SpotTrade spotTrade = new SpotTrade();
        spotTrade.setTradeDate(LocalDate.now().toString());
        
        CommodityType[] commodity = CommodityType.values();
        int randomIndex = random.nextInt(commodity.length);
        spotTrade.setCommodityType(commodity[randomIndex]);
        
        spotTrade.setCounterparty(parties.get(random.nextInt(parties.size())));
        spotTrade.setQuantity(random.nextInt(200));
        spotTrade.setPrice(random.nextInt(1000));
        spotTrade.setTradeType(TradeType.SPOT);
        return spotTrade;
    }

    private FuturesTrade createRandomFuturesTrade() {
        FuturesTrade futuresTrade = new FuturesTrade();
        futuresTrade.setTradeDate(LocalDate.now().toString());
        
        CommodityType[] commodity = CommodityType.values();
        int randomIndex = random.nextInt(commodity.length);
        futuresTrade.setCommodityType(commodity[randomIndex]);
        
        futuresTrade.setCounterparty(parties.get(random.nextInt(parties.size())));
        futuresTrade.setPrice(random.nextInt(1000));
        futuresTrade.setQuantity(random.nextInt(200));
        futuresTrade.setDiscountRate(random.nextDouble() * 0.1);
        futuresTrade.setDaysToSettlement(random.nextInt(365));
        futuresTrade.setTradeType(TradeType.FUTURES);
        return futuresTrade;
    }

    private void saveTrade(Trade trade) {
        TradeService ts = new TradeService();
        ts.saveTrade(trade);
    }
}
