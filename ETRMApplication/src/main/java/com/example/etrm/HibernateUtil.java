package com.example.etrm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class provides util functions for the Hibernate Object Relational Mapping (ORM)
 */
public class HibernateUtil {
//	The SessionFactory object
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Method to build and set the SessionFactory object
     * Calls dbconfig to get local database connection details
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Create Configuration instance from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Load properties from dbconfig.properties
            Properties dbProperties = new Properties();
            InputStream inputStream = HibernateUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            dbProperties.load(inputStream);
            inputStream.close();

            // Set properties for Hibernate Configuration
            String driverClass = dbProperties.getProperty("db.driver");
            if (driverClass != null) {
                configuration.setProperty("hibernate.connection.driver_class", driverClass);
            } else {
                throw new RuntimeException("db.driver property not found in dbconfig.properties");
            }
            
            String url = dbProperties.getProperty("db.url");
            if (url != null) {
                configuration.setProperty("hibernate.connection.url", url);
            } else {
                throw new RuntimeException("db.url property not found in dbconfig.properties");
            }

            String username = dbProperties.getProperty("db.username");
            if (username != null) {
                configuration.setProperty("hibernate.connection.username", username);
            } else {
                throw new RuntimeException("db.username property not found in dbconfig.properties");
            }

            String password = dbProperties.getProperty("db.password");
            if (password != null) {
                configuration.setProperty("hibernate.connection.password", password);
            } else {
                throw new RuntimeException("db.password property not found in dbconfig.properties");
            }
            
            // Build SessionFactory
            return configuration.buildSessionFactory();
        } catch (IOException ex) {
            System.err.println("Error reading dbconfig.properties: " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns the SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Terminates the SessionFactory
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
