package com.example.etrm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpotTradeTest {

    private SpotTrade spotTrade;

    @Before
    public void setUp() {
        spotTrade = new SpotTrade();
    }

    @Test
    public void testEmptyConstructor() {
        assertNotNull(spotTrade);
    }

    @Test
    public void testConstructorWithAllFields() {
        TradeType tradeType = TradeType.SPOT;
        Trade.BuySell buySell = Trade.BuySell.BUY;
        String tradeDate = "2023-06-01";
        CommodityType commodityType = CommodityType.CRUDE_OIL;
        CounterpartyService cs = new CounterpartyService();
		Counterparty counterparty = cs.getCounterparty(1);
        double price = 120.0;
        int quantity = 15;

        SpotTrade trade = new SpotTrade(tradeType, buySell, tradeDate, commodityType, counterparty, price, quantity);

        assertNotNull(trade);
        assertEquals(tradeType, trade.getTradeType());
        assertEquals(buySell, trade.getBuySell());
        assertEquals(tradeDate, trade.getTradeDate());
        assertEquals(commodityType, trade.getCommodityType());
        assertEquals(counterparty, trade.getCounterparty());
        assertEquals(price, trade.getPrice(), 0.001);
        assertEquals(quantity, trade.getQuantity());
    }

    @Test
    public void testGettersAndSetters() {
        TradeType tradeType = TradeType.SPOT;
        Trade.BuySell buySell = Trade.BuySell.BUY;
        String tradeDate = "2023-06-01";
        CommodityType commodityType = CommodityType.CRUDE_OIL;
        CounterpartyService cs = new CounterpartyService();
		Counterparty counterparty = cs.getCounterparty(1);
        double price = 120.0;
        int quantity = 15;

        spotTrade.setTradeType(tradeType);
        spotTrade.setBuySell(buySell);
        spotTrade.setTradeDate(tradeDate);
        spotTrade.setCommodityType(commodityType);
        spotTrade.setCounterparty(counterparty);
        spotTrade.setPrice(price);
        spotTrade.setQuantity(quantity);

        assertEquals(tradeType, spotTrade.getTradeType());
        assertEquals(buySell, spotTrade.getBuySell());
        assertEquals(tradeDate, spotTrade.getTradeDate());
        assertEquals(commodityType, spotTrade.getCommodityType());
        assertEquals(counterparty, spotTrade.getCounterparty());
        assertEquals(price, spotTrade.getPrice(), 0.001);
        assertEquals(quantity, spotTrade.getQuantity());
    }

}
