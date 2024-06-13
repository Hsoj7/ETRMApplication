package com.example.etrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class FuturesTradeTest {

    private FuturesTrade futuresTrade;

    @Before
    public void setUp() {
        TradeType tradeType = TradeType.FUTURES;
        Trade.BuySell buySell = Trade.BuySell.BUY;
        String tradeDate = "2023-06-01";
        CommodityType commodityType = CommodityType.CRUDE_OIL;
        CounterpartyService cs = new CounterpartyService();
        Counterparty counterparty = cs.getCounterparty(1);
        double price = 1500.0;
        int quantity = 10;
        double discountRate = 0.05;
        int daysToSettlement = 30;

        futuresTrade = new FuturesTrade(tradeType, buySell, tradeDate, commodityType, counterparty, price, quantity, discountRate, daysToSettlement);
    }

    @Test
    public void testConstructorWithAllFields() {
        assertNotNull(futuresTrade);
        assertEquals(TradeType.FUTURES, futuresTrade.getTradeType());
        assertEquals(Trade.BuySell.BUY, futuresTrade.getBuySell());
        assertEquals("2023-06-01", futuresTrade.getTradeDate());
        assertEquals(CommodityType.CRUDE_OIL, futuresTrade.getCommodityType());
        assertEquals(1, futuresTrade.getCounterparty().getId());
        assertEquals(1500.0, futuresTrade.getPrice(), 0.001);
        assertEquals(10, futuresTrade.getQuantity());
        assertEquals(0.05, futuresTrade.getDiscountRate(), 0.001);
        assertEquals(30, futuresTrade.getDaysToSettlement());
    }

    @Test
    public void testGettersAndSetters() {
        TradeType tradeType = TradeType.FUTURES;
        Trade.BuySell buySell = Trade.BuySell.SELL;
        String tradeDate = "2023-06-02";
        CommodityType commodityType = CommodityType.CRUDE_OIL;
        CounterpartyService cs = new CounterpartyService();
        Counterparty counterparty = cs.getCounterparty(2);
        double price = 1600.0;
        int quantity = 20;
        double discountRate = 0.06;
        int daysToSettlement = 35;

        futuresTrade.setTradeType(tradeType);
        futuresTrade.setBuySell(buySell);
        futuresTrade.setTradeDate(tradeDate);
        futuresTrade.setCommodityType(commodityType);
        futuresTrade.setCounterparty(counterparty);
        futuresTrade.setPrice(price);
        futuresTrade.setQuantity(quantity);
        futuresTrade.setDiscountRate(discountRate);
        futuresTrade.setDaysToSettlement(daysToSettlement);

        assertEquals(tradeType, futuresTrade.getTradeType());
        assertEquals(buySell, futuresTrade.getBuySell());
        assertEquals(tradeDate, futuresTrade.getTradeDate());
        assertEquals(commodityType, futuresTrade.getCommodityType());
        assertEquals(counterparty, futuresTrade.getCounterparty());
        assertEquals(price, futuresTrade.getPrice(), 0.001);
        assertEquals(quantity, futuresTrade.getQuantity());
        assertEquals(discountRate, futuresTrade.getDiscountRate(), 0.001);
        assertEquals(daysToSettlement, futuresTrade.getDaysToSettlement());
    }

}
