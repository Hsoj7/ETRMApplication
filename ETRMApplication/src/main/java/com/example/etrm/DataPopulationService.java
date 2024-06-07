package com.example.etrm;

import java.time.LocalDate;
import java.util.Random;

public class DataPopulationService {

    private static final String[] COMMODITY_TYPES = {"Oil", "Gas"};
    private static final String[] COUNTERPARTIES = {"Suncor", "Cenovus", "Canadian Natural", "TC Energy"};

    public DataPopulationService() {

    }

    public void populateDatabase(int numberOfSpotTrades, int numberOfFuturesTrades) {
        for (int i = 0; i < numberOfSpotTrades; i++) {
            SpotTrade spotTrade = createRandomSpotTrade();
            saveTrade(spotTrade);
        }

        for (int i = 0; i < numberOfFuturesTrades; i++) {
            FuturesTrade futuresTrade = createRandomFuturesTrade();
            saveTrade(futuresTrade);
        }
    }

    private SpotTrade createRandomSpotTrade() {
        Random random = new Random();
        SpotTrade spotTrade = new SpotTrade();
        spotTrade.setTradeDate(LocalDate.now().toString());
        spotTrade.setCommodityType(COMMODITY_TYPES[random.nextInt(COMMODITY_TYPES.length)]);
        spotTrade.setCounterparty(COUNTERPARTIES[random.nextInt(COUNTERPARTIES.length)]);
        spotTrade.setQuantity(random.nextInt(200));
        spotTrade.setPrice(random.nextInt(1000));
        spotTrade.setTradeType(TradeType.SPOT);
        return spotTrade;
    }

    private FuturesTrade createRandomFuturesTrade() {
        Random random = new Random();
        FuturesTrade futuresTrade = new FuturesTrade();
        futuresTrade.setTradeDate(LocalDate.now().toString());
        futuresTrade.setCommodityType(COMMODITY_TYPES[random.nextInt(COMMODITY_TYPES.length)]);
        futuresTrade.setCounterparty(COUNTERPARTIES[random.nextInt(COUNTERPARTIES.length)]);
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
