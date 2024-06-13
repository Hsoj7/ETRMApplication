package com.example.etrm;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.etrm.Trade.BuySell;

import java.util.List;

public class TradeServiceTest {

    private TradeService tradeService;
    private CounterpartyService cs;

    @Before
    public void setUp() {
        // Initialize the TradeService with H2 configuration
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory("hibernate-test.cfg.xml");
        tradeService = new TradeService(sessionFactory);
        cs = new CounterpartyService(sessionFactory);

        //  Insert a temp Counterparty
        Counterparty cp = new Counterparty("Test Company", "AA", 100000.00, 10000);
        cs.saveCounterparty(cp);
    }

    @After
    public void tearDown() {
        // Clean up resources
        HibernateUtil.shutdown();
    }
    
    @Test
    public void testSaveTrade() {
    	TradeType tradeType = TradeType.SPOT;
        Trade.BuySell buySell = Trade.BuySell.BUY;
        String tradeDate = "2023-06-01";
        CommodityType commodityType = CommodityType.CRUDE_OIL;
		Counterparty counterparty = cs.getCounterparty(1);
        double price = 120.0;
        int quantity = 15;

        SpotTrade spotTrade = new SpotTrade(tradeType, buySell, tradeDate, commodityType, counterparty, price, quantity);
        tradeService.saveTrade(spotTrade);

        SpotTrade savedTrade = tradeService.getRecentSpotTrade();
        assertNotNull(savedTrade);
        assertEquals(spotTrade.getId(), savedTrade.getId());
    }
    	
    @Test
    public void testGetSpotTrade() {
		Counterparty counterparty = cs.getCounterparty(1);

        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, Trade.BuySell.BUY, "2024-06-06", CommodityType.CRUDE_OIL, counterparty, 75.0, 1000);
        tradeService.saveTrade(spotTrade);

        SpotTrade fetchedSpotTrade = tradeService.getSpotTrade(spotTrade.getId());
        assertNotNull(fetchedSpotTrade);
        assertEquals(75.0, fetchedSpotTrade.getPrice(), 0.0);
        assertEquals(1000, fetchedSpotTrade.getQuantity());
    }

    @Test
    public void testGetFuturesTrade() {
    	Counterparty counterparty = cs.getCounterparty(1);
    	
        FuturesTrade futuresTrade = new FuturesTrade(TradeType.FUTURES, Trade.BuySell.BUY, "2024-06-06", CommodityType.CRUDE_OIL, counterparty, 1200.0, 500, 0.05, 30);
        tradeService.saveTrade(futuresTrade);

        FuturesTrade fetchedFuturesTrade = tradeService.getFuturesTrade(futuresTrade.getId());
        assertNotNull(fetchedFuturesTrade);
        assertEquals(1200.0, fetchedFuturesTrade.getPrice(), 0.0);
        assertEquals(500, fetchedFuturesTrade.getQuantity());
        assertEquals(0.05, fetchedFuturesTrade.getDiscountRate(), 0.0);
        assertEquals(30, fetchedFuturesTrade.getDaysToSettlement());
    }

    @Test
    public void testUpdateTrade() {
    	Counterparty counterparty = cs.getCounterparty(1);

        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, Trade.BuySell.BUY, "2024-06-06", CommodityType.CRUDE_OIL, counterparty, 75.0, 1000);
        tradeService.saveTrade(spotTrade);

        spotTrade.setPrice(80.0);
        tradeService.updateTrade(spotTrade);

        SpotTrade updatedSpotTrade = tradeService.getSpotTrade(spotTrade.getId());
        assertNotNull(updatedSpotTrade);
        assertEquals(80.0, updatedSpotTrade.getPrice(), 0.0);
    }

    @Test
    public void testDeleteTrade() {
    	Counterparty counterparty = cs.getCounterparty(1);

        SpotTrade spotTrade = new SpotTrade(TradeType.SPOT, Trade.BuySell.BUY, "2024-06-06", CommodityType.CRUDE_OIL, counterparty, 75.0, 1000);
        tradeService.saveTrade(spotTrade);
        
        SpotTrade trade = tradeService.getRecentSpotTrade();
        tradeService.deleteTrade(trade.getId());

        SpotTrade deletedSpotTrade = tradeService.getSpotTrade(spotTrade.getId());
        assertNull(deletedSpotTrade);
    }
}
