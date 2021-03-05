package pl.edu.wszib.bryan.air.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.bryan.air.dao.IFlightDAO;
import pl.edu.wszib.bryan.air.model.Flight;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateFlightDAOImpl implements IFlightDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Flight getFlightById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Flight> query = session.createQuery("FROM pl.edu.wszib.bryan.air.model.Flight WHERE id = :id");
        query.setParameter("id", id);
        Flight book = null;
        try {
            book = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nie znaleziono lotu !!");
        }
        session.close();
        return book;
    }

    @Override
    public void updateFlight(Flight flight) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(flight);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Flight> getAllFlights() {
        Session session = this.sessionFactory.openSession();
        Query<Flight> query = session.createQuery("FROM pl.edu.wszib.bryan.air.model.Flight");
        List<Flight> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public void persistFlight(Flight flight) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(flight);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeFlight(Flight flight) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(flight);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}

