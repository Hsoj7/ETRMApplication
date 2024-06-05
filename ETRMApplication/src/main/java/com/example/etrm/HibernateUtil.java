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
    // The SessionFactory object
    private static final SessionFactory sessionFactory = buildSessionFactory("hibernate.cfg.xml");

    /**
     * Method to build and set the SessionFactory object using the default configuration file
     */
    private static SessionFactory buildSessionFactory() {
        return buildSessionFactory("hibernate.cfg.xml");
    }

    /**
     * Method to build and set the SessionFactory object using a specified configuration file
     * Calls dbconfig to get local database connection details for the default configuration
     */
    private static SessionFactory buildSessionFactory(String configFileName) {
        try {
            // Create Configuration instance from the specified hibernate configuration file
            Configuration configuration = new Configuration();
            configuration.configure(configFileName);

            // If the default configuration is used, load properties from dbconfig.properties
            if ("hibernate.cfg.xml".equals(configFileName)) {
                // Load properties from dbconfig.properties
                Properties dbProperties = new Properties();
                InputStream inputStream = HibernateUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
                if (inputStream != null) {
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
                } else {
                    throw new RuntimeException("dbconfig.properties file not found in classpath");
                }
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
     * Returns the SessionFactory with a specified configuration file
     */
    public static SessionFactory getSessionFactory(String configFileName) {
        return buildSessionFactory(configFileName);
    }

    /**
     * Terminates the SessionFactory
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
