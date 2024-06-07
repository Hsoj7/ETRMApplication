package com.example.etrm;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
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
        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A", 75.0, 1000);
        tradeService.saveTrade(spotTrade);

        Trade savedTrade = tradeService.getTrade(spotTrade.getId());
        assertNotNull(savedTrade);
        assertEquals("SPOT", savedTrade.getTradeType().toString());
        assertEquals("2023-06-01", savedTrade.getTradeDate());
        assertEquals("Oil", savedTrade.getCommodityType());
        assertEquals("Counterparty A", savedTrade.getCounterparty());
    }

    @Test
    public void testGetSpotTrade() {
        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A", 75.0, 1000);
        tradeService.saveTrade(spotTrade);

        SpotTrade fetchedSpotTrade = tradeService.getSpotTrade(spotTrade.getId());
        assertNotNull(fetchedSpotTrade);
        assertEquals(75.0, fetchedSpotTrade.getPrice(), 0.0);
        assertEquals(1000, fetchedSpotTrade.getQuantity());
    }

    @Test
    public void testGetFuturesTrade() {
        FuturesTrade futuresTrade = new FuturesTrade(TradeType.FUTURES, "2023-06-02", "Gold", "Counterparty B", 1200.0, 500, 0.05, 30);
        tradeService.saveTrade(futuresTrade);

        FuturesTrade fetchedFuturesTrade = tradeService.getFuturesTrade(futuresTrade.getId());
        assertNotNull(fetchedFuturesTrade);
        assertEquals(1200.0, fetchedFuturesTrade.getPrice(), 0.0);
        assertEquals(500, fetchedFuturesTrade.getQuantity());
        assertEquals(0.05, fetchedFuturesTrade.getDiscountRate(), 0.0);
        assertEquals(30, fetchedFuturesTrade.getDaysToSettlement());
    }

    @Test
    public void testGetAllTrades() {
        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A", 75.0, 1000);
        FuturesTrade futuresTrade = new FuturesTrade(TradeType.FUTURES, "2023-06-02", "Gold", "Counterparty B", 1200.0, 500, 0.05, 30);

        tradeService.saveTrade(spotTrade);
        tradeService.saveTrade(futuresTrade);

        List<Trade> allTrades = tradeService.getAllTrades();
        assertEquals(2, allTrades.size());
    }

    @Test
    public void testGetAllSpotTrades() {
        SpotTrade spotTrade1 = new SpotTrade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A", 75.0, 1000);
        SpotTrade spotTrade2 = new SpotTrade(TradeType.SPOT, "2023-06-02", "Silver", "Counterparty C", 50.0, 2000);

        tradeService.saveTrade(spotTrade1);
        tradeService.saveTrade(spotTrade2);

        List<SpotTrade> allSpotTrades = tradeService.getAllSpotTrades();
        assertEquals(2, allSpotTrades.size());
    }

    @Test
    public void testGetAllFuturesTrades() {
        FuturesTrade futuresTrade1 = new FuturesTrade(TradeType.FUTURES, "2023-06-02", "Gold", "Counterparty B", 1200.0, 500, 0.05, 30);
        FuturesTrade futuresTrade2 = new FuturesTrade(TradeType.FUTURES, "2023-06-03", "Platinum", "Counterparty D", 1500.0, 300, 0.04, 60);

        tradeService.saveTrade(futuresTrade1);
        tradeService.saveTrade(futuresTrade2);

        List<FuturesTrade> allFuturesTrades = tradeService.getAllFuturesTrades();
        assertEquals(2, allFuturesTrades.size());
    }

    @Test
    public void testUpdateTrade() {
        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A", 75.0, 1000);
        tradeService.saveTrade(spotTrade);

        spotTrade.setPrice(80.0);
        tradeService.updateTrade(spotTrade);

        SpotTrade updatedSpotTrade = tradeService.getSpotTrade(spotTrade.getId());
        assertNotNull(updatedSpotTrade);
        assertEquals(80.0, updatedSpotTrade.getPrice(), 0.0);
    }

    @Test
    public void testDeleteTrade() {
        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A", 75.0, 1000);
        tradeService.saveTrade(spotTrade);

        tradeService.deleteTrade(spotTrade.getId());

        SpotTrade deletedSpotTrade = tradeService.getSpotTrade(spotTrade.getId());
        assertNull(deletedSpotTrade);
    }

    @Test
    public void testGetRecentSpotTrade() {
        SpotTrade spotTrade1 = new SpotTrade(TradeType.SPOT, "2023-06-01", "Oil", "Counterparty A", 75.0, 1000);
        SpotTrade spotTrade2 = new SpotTrade(TradeType.SPOT, "2023-06-02", "Silver", "Counterparty C", 50.0, 2000);

        tradeService.saveTrade(spotTrade1);
        tradeService.saveTrade(spotTrade2);

        SpotTrade recentSpotTrade = tradeService.getRecentSpotTrade();
        assertNotNull(recentSpotTrade);
        assertEquals("Silver", recentSpotTrade.getCommodityType());
    }

    @Test
    public void testGetRecentFuturesTrade() {
        FuturesTrade futuresTrade1 = new FuturesTrade(TradeType.FUTURES, "2023-06-02", "Gold", "Counterparty B", 1200.0, 500, 0.05, 30);
        FuturesTrade futuresTrade2 = new FuturesTrade(TradeType.FUTURES, "2023-06-03", "Platinum", "Counterparty D", 1500.0, 300, 0.04, 60);

        tradeService.saveTrade(futuresTrade1);
        tradeService.saveTrade(futuresTrade2);

        FuturesTrade recentFuturesTrade = tradeService.getRecentFuturesTrade();
        assertNotNull(recentFuturesTrade);
        assertEquals("Platinum", recentFuturesTrade.getCommodityType());
    }
}
