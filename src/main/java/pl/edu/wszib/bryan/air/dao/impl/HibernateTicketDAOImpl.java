package pl.edu.wszib.bryan.air.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.bryan.air.dao.ITicketDAO;
import pl.edu.wszib.bryan.air.model.Ticket;

@Repository
public class HibernateTicketDAOImpl implements ITicketDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(ticket);
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
    public Ticket getTicketById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Ticket> query = session.createQuery("FROM pl.edu.wszib.bryan.air.model.Ticket WHERE id = :id");
        query.setParameter("id", id);
        Ticket order = query.getSingleResult();
        session.close();
        return order;
    }
}
