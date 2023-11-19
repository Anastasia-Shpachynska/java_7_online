package dao.Impl;

import config.HibernateConfig;
import dao.FlightDao;
import entity.Flight;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class FlightDaoImpl implements FlightDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Flight entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()){
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Flight entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Flight flight = session.find(Flight.class, id);
            if(flight != null) {
                session.delete(flight);
            }
            transaction.commit();
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Flight findOne(Long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Flight flight = session.find(Flight.class, id);
            transaction.commit();
            return flight;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Flight> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Flight ");
            List<Flight> flights = query.getResultList();
            transaction.commit();
            return flights;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean existsById(Long id) {
        return true;
    }
}
