package com.example.etrm;

import jakarta.persistence.*;

/**
 * This class represents a Counterparty entity
 */
@Entity
@Table(name = "counterparties")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "credit_rating")
    private String creditRating;

    @Column(name = "credit_limit")
    private double creditLimit;
    
    @Column(name = "position_limits")
    private int positionLimits;

    public Counterparty() {
        // Default constructor
    }

    public Counterparty(String name, String creditRating, double creditLimit, int positionLimits) {
        this.name = name;
        this.creditRating = creditRating;
        this.creditLimit = creditLimit;
        this.positionLimits = positionLimits;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getPositionLimits() {
        return positionLimits;
    }

    public void setPositionLimits(int positionLimits) {
        this.positionLimits = positionLimits;
    }
}
