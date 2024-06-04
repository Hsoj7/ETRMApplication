package com.example.etrm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TradeService {
    private SessionFactory sessionFactory;

    public TradeService() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Trade getTrade(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Trade.class, id);
        }
    }
    
    public void saveTrade(Trade trade) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(trade);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Trade> getAllTrades() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Trade", Trade.class).list();
        }
    }

    public void updateTrade(Trade trade) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(trade);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTrade(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Trade trade = session.get(Trade.class, id);
            if (trade != null) {
                session.delete(trade);
                System.out.println("Trade is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
