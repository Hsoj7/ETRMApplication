package com.example.etrm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * This class implements CRUD operations for the Counterparty entity
 */
public class CounterpartyService {

    private SessionFactory sessionFactory;

    /**
     * Constructs a TradeService object with the default SessionFactory
     */
    public CounterpartyService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    /**
     * Constructs a CounterpartyService object with a specified SessionFactory
     * Give the option to pass a separate hibernate config file for testing - needed to accomodate H2 temp database
     * @param sessionFactory The SessionFactory to be used by this TradeService
     */
    public CounterpartyService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveCounterparty(Counterparty counterparty) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(counterparty);
            tx.commit();
            System.out.println("Counterparty saved.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Counterparty getCounterparty(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Counterparty.class, id);
        }
    }

    public List<Counterparty> getAllCounterparties() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Counterparty", Counterparty.class).list();
        }
    }

    public void updateCounterparty(Counterparty counterparty) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(counterparty);
            tx.commit();
            System.out.println("Counterparty updated.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteCounterparty(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Counterparty counterparty = session.get(Counterparty.class, id);
            if (counterparty != null) {
                session.remove(counterparty);
            }
            tx.commit();
            System.out.println("Counterparty deleted.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
