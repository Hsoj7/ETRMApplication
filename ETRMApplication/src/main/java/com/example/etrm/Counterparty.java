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

    @Column(name = "current_exposure")
    private double currentExposure;

    public Counterparty() {
        // Default constructor
    }

    public Counterparty(String name, String creditRating, double creditLimit, double currentExposure) {
        this.name = name;
        this.creditRating = creditRating;
        this.creditLimit = creditLimit;
        this.currentExposure = currentExposure;
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

    public double getCurrentExposure() {
        return currentExposure;
    }

    public void setCurrentExposure(double currentExposure) {
        this.currentExposure = currentExposure;
    }
}
