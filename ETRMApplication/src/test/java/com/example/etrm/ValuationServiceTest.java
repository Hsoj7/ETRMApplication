package com.example.etrm;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ValuationServiceTest {

    private ValuationService valuationService;

    @Before
    public void setUp() {
        valuationService = new ValuationService();
    }

    @Test
    public void testValueSpotTrade() {
        SpotTrade spotTrade = new SpotTrade();
        spotTrade.setPrice(100.0);
        spotTrade.setQuantity(10);
        
        double expectedValue = 100.0 * 10.0;
        double actualValue = valuationService.valueSpotTrade(spotTrade);
        
        assertEquals(expectedValue, actualValue, 0.001);
    }

    @Test
    public void testValueFuturesTrade() {
        FuturesTrade futuresTrade = new FuturesTrade();
        futuresTrade.setPrice(200.0);
        futuresTrade.setQuantity(5);
        futuresTrade.setDiscountRate(0.05);
        futuresTrade.setDaysToSettlement(30);
        
        double futurePrice = 200.0;
        double quantity = 5.0;
        double discountRate = 0.05;
        int daysToSettlement = 30;
        double expectedValue = futurePrice * quantity / Math.pow(1 + discountRate, daysToSettlement / 365.0);
        double actualValue = valuationService.valueFuturesTrade(futuresTrade);
        
        assertEquals(expectedValue, actualValue, 0.001);
    }

    @Test
    public void testValueFuturesTradeWithZeroDiscountRate() {
        FuturesTrade futuresTrade = new FuturesTrade();
        futuresTrade.setPrice(200.0);
        futuresTrade.setQuantity(5);
        futuresTrade.setDiscountRate(0.0);
        futuresTrade.setDaysToSettlement(30);
        
        double expectedValue = 200.0 * 5.0;
        double actualValue = valuationService.valueFuturesTrade(futuresTrade);
        
        assertEquals(expectedValue, actualValue, 0.001);
    }

    @Test
    public void testValueFuturesTradeWithZeroDaysToSettlement() {
        FuturesTrade futuresTrade = new FuturesTrade();
        futuresTrade.setPrice(200.0);
        futuresTrade.setQuantity(5);
        futuresTrade.setDiscountRate(0.05);
        futuresTrade.setDaysToSettlement(0);
        
        double expectedValue = 200.0 * 5.0;
        double actualValue = valuationService.valueFuturesTrade(futuresTrade);
        
        assertEquals(expectedValue, actualValue, 0.001);
    }
}
