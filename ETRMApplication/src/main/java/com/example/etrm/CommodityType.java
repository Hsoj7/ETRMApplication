package com.example.etrm;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;


public class CommodityType {
    private final String name;
    private final double targetUnitPrice;
    private final int targetQuantity;

    private static final Map<String, CommodityType> BY_NAME = new HashMap<>();

    // Private constructor to prevent instantiation
    private CommodityType(String name, double targetUnitPrice, int targetQuantity) {
        this.name = name;
        this.targetUnitPrice = targetUnitPrice;
        this.targetQuantity = targetQuantity;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getTargetUnitPrice() {
        return targetUnitPrice;
    }

    public int getTargetQuantity() {
        return targetQuantity;
    }

    // CommodityType constants
	//Crude oil expressed as $80/barrel   
	public static final CommodityType CRUDE_OIL = new CommodityType("CRUDE_OIL", 80.0, 12);
	// Natural Gas expressed as $0.11 per cubic meter   
	public static final CommodityType NATURAL_GAS = new CommodityType("NATURAL_GAS", 0.11, 9090);
	// Liquified Natural Gas expressed $9.25 per Thousand cubic feet
	public static final CommodityType LIQUIFIED_NATURAL_GAS = new CommodityType("LIQUIFIED_NATURAL_GAS", 9.25, 108);
	// Electricity expressed as $200 per 1000 kwh
	public static final CommodityType ELECTRICITY = new CommodityType("ELECTRICITY", 200, 5);
	// Renewable credits expressed as $1,000 per credit
	public static final CommodityType RENEWABLE_ENERGY_CREDITS = new CommodityType("RENEWABLE_ENERGY_CREDITS", 1000, 1);

    // Array of all commodity types
    public static final CommodityType[] VALUES = {
        CRUDE_OIL, NATURAL_GAS, LIQUIFIED_NATURAL_GAS, ELECTRICITY, RENEWABLE_ENERGY_CREDITS
    };
    
 // Static block to initialize the map
    static {
        for (CommodityType ct : VALUES) {
            BY_NAME.put(ct.getName(), ct);
        }
    }

    // Method to get a random CommodityType
    public static CommodityType getRandomCommodityType() {
        Random random = new Random();
        return VALUES[random.nextInt(VALUES.length)];
    }

    // Method to get CommodityType by name
    public static CommodityType getCommodityTypeByName(String name) {
        return BY_NAME.get(name);
    }
}
