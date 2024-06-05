package com.example.etrm;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TradeServiceTest {

    private TradeService tradeService;

    @Before
    public void setUp() {
        // Initialize the TradeService with H2 configuration
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory("hibernate-test.cfg.xml");
        tradeService = new TradeService(sessionFactory);
    }

    @After
    public void tearDown() {
        // Clean up resources
        HibernateUtil.shutdown();
    }

    @Test
    public void testSaveTrade() {
        Trade trade = new Trade("2023-01-01", "Gas", 1500, 100.0, "Test Company");
        tradeService.saveTrade(trade);

        Trade retrievedTrade = tradeService.getTrade(trade.getId());
        assertNotNull(retrievedTrade);
        assertEquals("2023-01-01", retrievedTrade.getTradeDate());
        assertEquals("Gas", retrievedTrade.getCommodityType());
        assertEquals(1500, retrievedTrade.getQuantity(), 0.01);
        assertEquals(100.0, retrievedTrade.getPrice(), 0.01);
        assertEquals("Test Company", retrievedTrade.getCounterparty());
    }

    @Test
    public void testGetTrade() {
        Trade trade = new Trade("2023-01-01", "Oil", 50, 76.25, "Counterparty B");
        tradeService.saveTrade(trade);

        Trade retrievedTrade = tradeService.getTrade(trade.getId());
        assertNotNull(retrievedTrade);
        assertEquals("2023-01-01", retrievedTrade.getTradeDate());
        assertEquals("Oil", retrievedTrade.getCommodityType());
        assertEquals(50, retrievedTrade.getQuantity(), 0.01);
        assertEquals(76.25, retrievedTrade.getPrice(), 0.01);
        assertEquals("Counterparty B", retrievedTrade.getCounterparty());
    }

    @Test
    public void testGetAllTrades() {
        tradeService.saveTrade(new Trade("2023-01-01", "Oil", 100, 80.50, "Counterparty A"));
        tradeService.saveTrade(new Trade("2023-01-02", "Electricity", 15.10, 401.25, "Counterparty B"));

        List<Trade> trades = tradeService.getAllTrades();
        assertNotNull(trades);
        assertTrue(trades.size() >= 2);
    }

    @Test
    public void testUpdateTrade() {
        Trade trade = new Trade("2023-01-01", "Plutonium", 10, 950.75, "Counterparty C");
        tradeService.saveTrade(trade);

        trade.setPrice(1050.00);
        tradeService.updateTrade(trade);

        Trade updatedTrade = tradeService.getTrade(trade.getId());
        assertNotNull(updatedTrade);
        assertEquals(1050.00, updatedTrade.getPrice(), 0.01);
    }

    @Test
    public void testDeleteTrade() {
        Trade trade = new Trade("2023-01-01", "Plutonium", 5, 1526.50, "Counterparty D");
        tradeService.saveTrade(trade);

        tradeService.deleteTrade(trade.getId());

        Trade deletedTrade = tradeService.getTrade(trade.getId());
        assertNull(deletedTrade);
    }
}
