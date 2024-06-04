# ETRM Simulation Application

# Background

## What is ETRM?
Energy/commodities trading & risk management systems
Systems bought by physical commodities trading firms & suppliers as well, could be miners, or oil explorers, etc.
It is a risk repository of any risk you might encounter when making trades. Risks include:
Price risk - the trade is not a fixed price
Physical risk - delivery based risk. Maybe the trade is going half-way around the world, think of recent Red Sea shipping disaster
Financial risk - clients defaulting on credit
Operational risk - mitigating any human errors
We’re tracking all this risk within the system to mitigate any problems before it’s too late

## How is ETRM used in an organization?
For big companies, they might have 100+, 1000+ ongoing trades at any time. Previously, all trade data would be kept in some form of spreadsheet, not accessible by everyone in the company. ETRM systems give everyone in the company the ability to see all risks among all trades at any given point.
For smaller companies, even in the start-up phase, they might need financing for trading operations and lenders want a full audit of risk of open positions. Margin is a bitch.
At the end of the day, they’re useful for data quality, realtime reporting, quarterly reporting, and most importantly managing risks.

## ETRM vs. CTRM
CTRM is any commodity, ETRM is specifically energy. ETRM is a subset of CTRM



# Introduction
This document outlines the development of a simplified Energy Trading and Risk Management (ETRM) system. The system will consist of modules for trade capture, trade valuation, risk management, and reporting. The initial phase will focus on basic functionality with potential for future expansion, including options trading.

# Project Overview
Objective: To build a basic ETRM system that can handle the essential functions of trade capture, valuation, risk management, and reporting. This system will be implemented in Java and will use a relational database for storage.

# System Architecture
Technology Stack:
Programming Language: Java SE 17
UI: Command-line
Database: MySQL
Object Relational Mapping: Hibernate
Build Tool: Maven
Testing Framework: JUnit
Version Control: GitHub
Charting Library: JFreeChart (optional, for reporting)

# Modules Description
## 0. Setup
Objective: To setup the environment with tools needed for development.
Implementation Steps:
Github: Initialize a remote repository for version control.
Database: Initialize the database environment and have it listen on a local port. Create a ‘trades’ table and populate it with data.
Test: use Main.java to ensure everything is setup and running.

## 1. Trade Class
Objective: To hold trade data within the program. 
Implementation Steps:
Entity Class: Define the Trade class including constructor and relevant methods.
Service Layer: Implement a TradeService class for business logic and interaction with the database. Implement CRUD operations using Object-Relational Mapping (ORM).
ORM: Use Hibernate, an ORM (object-relational mapping) tool for all CRUD operations of the TradeService class

## 2. Trade Valuation
Objective: To implement valuation models for different types of trades.
Valuation Models:
Spot Trade Valuation:
Value = Price * Quantity
Futures Trade Valuation:
Present Value = Future Price * Quantity / (1 + Discount Rate)^(Days to Settlement / 365)
Implementation Steps:
Valuation Service: Create a ValuationService class to handle trade valuations.
Integration: Integrate the valuation logic within the trade capture process to provide real-time valuation.

## 3. Risk Management
Objective: To develop risk metrics like Value at Risk (VaR) and position limits.
Risk Metrics:
Value at Risk (VaR): Estimate potential loss over a specified period at a given confidence level.
Position Limits: Ensure that no single commodity or counterparty exceeds predefined exposure limits.
Implementation Steps:
Risk Management Service: Develop a RiskManagementService class to calculate and monitor risk metrics.
Integration: Integrate risk calculations with trade capture and valuation processes.

## 4. Reporting
Objective: To generate reports on trade positions, profit and loss (P&L), and risk metrics.

## Project Phases
Phase 1: Trade Capture
Set up project structure and dependencies.
Implement trade capture module.
Phase 2: Trade Valuation
Develop and integrate valuation models.
Implement valuation service.
Phase 3: Risk Management
Develop risk management metrics.
Integrate risk management with existing modules.
Phase 4: Reporting
Implement reporting functionality.
Generate sample reports and integrate with UI.

## Future Enhancements
Options Pricing: Extend the system to handle options trading and implement the Black-Scholes model.
Advanced Risk Metrics: Incorporate more sophisticated risk management techniques.
Enhanced UI: Develop a more user-friendly interface with advanced filtering and sorting options.
