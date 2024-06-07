package com.example.etrm;

import static org.junit.Assert.*;

import org.junit.Test;

public class TradeTest {

    @Test
    public void testTradeConstructorWithoutId() {
        Trade trade = new Trade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A");
        
        assertNotNull(trade);
        assertEquals(TradeType.SPOT, trade.getTradeType());
        assertEquals("2023-06-01", trade.getTradeDate());
        assertEquals("Oil", trade.getCommodityType());
        assertEquals("Counterparty A", trade.getCounterparty());
    }

    @Test
    public void testTradeConstructorWithId() {
        Trade trade = new Trade(1, TradeType.FUTURES, "2023-06-02", "Gold", "Counterparty B");
        
        assertNotNull(trade);
        assertEquals(1, trade.getId());
        assertEquals(TradeType.FUTURES, trade.getTradeType());
        assertEquals("2023-06-02", trade.getTradeDate());
        assertEquals("Gold", trade.getCommodityType());
        assertEquals("Counterparty B", trade.getCounterparty());
    }

    @Test
    public void testSettersAndGetters() {
        Trade trade = new Trade();
        
        trade.setTradeType(TradeType.SPOT);
        trade.setTradeDate("2023-06-01");
        trade.setCommodityType("Oil");
        trade.setCounterparty("Counterparty A");
        
        assertEquals(TradeType.SPOT, trade.getTradeType());
        assertEquals("2023-06-01", trade.getTradeDate());
        assertEquals("Oil", trade.getCommodityType());
        assertEquals("Counterparty A", trade.getCounterparty());
    }

    @Test
    public void testToString() {
        Trade trade = new Trade(1, TradeType.FUTURES, "2023-06-02", "Gold", "Counterparty B");
        
        String expected = "Trade{id=1, tradeType='FUTURES', tradeDate='2023-06-02', commodityType='Gold', counterparty='Counterparty B'}";
        assertEquals(expected, trade.toString());
    }

    @Test
    public void testEmptyConstructor() {
        Trade trade = new Trade();
        assertNotNull(trade);
    }
}
