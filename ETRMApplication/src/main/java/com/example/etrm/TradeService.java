package com.example.etrm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.mysql.cj.Query;

import java.util.List;

/**
 * This class implements Object-Relational Mapping (ORM) CRUD operations for the trades table
 * It provides methods to Create, Read, Update, and Delete
 */
public class TradeService {
    // Hibernate's implementation for object mappings
    private SessionFactory sessionFactory;

    /**
     * Constructs a TradeService object with the default SessionFactory
     */
    public TradeService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * Constructs a TradeService object with a specified SessionFactory
     * Give the option to pass a separate hibernate config file for testing - needed to accomodate H2 temp database
     * @param sessionFactory The SessionFactory to be used by this TradeService
     */
    public TradeService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves a Trade to the database
     * @param trade The trade object to save to the DB
     */
    public void saveTrade(Trade trade) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(trade);
            tx.commit();
            System.out.println("Trade saved.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    /**
     * Returns a SpotTrade from the database
     * @param id        	The id of the trade to get
     * @return SpotTrade 	The SpotTrade object representing the matching row
     */
    public Trade getTrade(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Trade.class, id);
        }
    }
    
    /**
     * Returns a SpotTrade from the database
     * @param id        	The id of the trade to get
     * @return SpotTrade 	The SpotTrade object representing the matching row
     */
    public SpotTrade getSpotTrade(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(SpotTrade.class, id);
        }
    }

    /**
     * Returns a FuturesTrade from the database
     * @param id        	The id of the trade to get
     * @return FuturesTrade The FuturesTrade object representing the matching row
     */
    public FuturesTrade getFuturesTrade(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(FuturesTrade.class, id);
        }
    }

    
    /**
     * Gets the most recently inserted trade from the db
     * @return	the Trade object representing the row most recently inserted
     * Not working since updating structure to trades as parent table and spot_trades/futures_trades as children
     */
//    public Trade getMostRecentTrade() {
//        Trade mostRecentTrade = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            // Start the transaction
//            Transaction transaction = session.beginTransaction();
//
//            try {
//                // Get the last inserted ID
//                NativeQuery<Integer> idQuery = session.createNativeQuery("SELECT LAST_INSERT_ID()");
//                System.out.println(idQuery.uniqueResult());
////                casting 
//                Integer lastInsertedId = ((Number) idQuery.uniqueResult()).intValue();
//
//                if (lastInsertedId != null) {
//                    // Retrieve the trade with the last inserted ID using the existing method
//                    mostRecentTrade = getTrade(lastInsertedId);
//                }
//
//                // Commit the transaction
//                transaction.commit();
//            } catch (Exception e) {
//                // Rollback the transaction in case of an error
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//                throw e;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return mostRecentTrade;
//    }
    
    

    /**
     * Returns a list of all trades from the database
     * This will include both spot trades and futures trades, ordered by ID ASC
     */
    public List<Trade> getAllTrades() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Trade", Trade.class).list();
        }
    }
    
    /**
     * Returns a list of all spot trades from the database
     */
    public List<SpotTrade> getAllSpotTrades() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from SpotTrade", SpotTrade.class).list();
        }
    }
    
    /**
     * Returns a list of all futures trades from the database
     */
    public List<FuturesTrade> getAllFuturesTrades() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from FuturesTrade", FuturesTrade.class).list();
        }
    }

    /**
     * Updates a trade based on primary key (id), creates a trade entry if none exist
     * @param trade the trade object with data to be updated
     */
    public void updateTrade(Trade trade) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(trade);
            transaction.commit();
            System.out.println("Trade updated.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Deletes the target trade
     * @param id The id of the trade to delete
     */
    public void deleteTrade(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Trade trade = session.get(Trade.class, id);
            if (trade != null) {
                session.remove(trade);
                System.out.println("Trade deleted.");
            } else {
                System.out.println("Trade not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Closes the SessionFactory
     */
    public void close() {
        sessionFactory.close();
    }
}
