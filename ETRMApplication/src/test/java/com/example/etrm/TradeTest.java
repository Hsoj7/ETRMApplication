package com.example.etrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TradeTest {

    private Trade trade;

    @Before
    public void setUp() {
        trade = new Trade("2024-06-04", "Oil", 1500.0, 100.0, "Counterparty A");
    }

    @Test
    public void testTradeConstructorWithId() {
        Trade tradeWithId = new Trade(1, "2024-06-04", "Oil", 1500.0, 100.0, "Counterparty A");
        assertEquals(1, tradeWithId.getId());
        assertEquals("2024-06-04", tradeWithId.getTradeDate());
        assertEquals("Oil", tradeWithId.getCommodityType());
        assertEquals(1500.0, tradeWithId.getQuantity(), 0);
        assertEquals(100.0, tradeWithId.getPrice(), 0);
        assertEquals("Counterparty A", tradeWithId.getCounterparty());
    }

    @Test
    public void testTradeConstructorWithoutId() {
        assertEquals(0, trade.getId());
        assertEquals("2024-06-04", trade.getTradeDate());
        assertEquals("Oil", trade.getCommodityType());
        assertEquals(1500.0, trade.getQuantity(), 0);
        assertEquals(100.0, trade.getPrice(), 0);
        assertEquals("Counterparty A", trade.getCounterparty());
    }

    @Test
    public void testSettersAndGetters() {
        trade.setTradeDate("2024-06-05");
        assertEquals("2024-06-05", trade.getTradeDate());

        trade.setCommodityType("Electricity");
        assertEquals("Electricity", trade.getCommodityType());

        trade.setQuantity(1200.0);
        assertEquals(1200.0, trade.getQuantity(), 0);

        trade.setPrice(150.0);
        assertEquals(150.0, trade.getPrice(), 0);

        trade.setCounterparty("Counterparty B");
        assertEquals("Counterparty B", trade.getCounterparty());
    }

}
