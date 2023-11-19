package dao.Impl;

import config.HibernateConfig;
import dao.AirportDao;
import entity.Airport;
import entity.Flight;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class AirportDaoImpl implements AirportDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Airport entity) {
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
    public void update(Airport entity) {
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
            Airport airport = session.find(Airport.class, id);
            if (airport != null) {
                session.delete(airport);
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
    public Airport findOne(Long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Airport airport = session.find(Airport.class, id);
            transaction.commit();
            return airport;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Airport> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Airport ");
            List<Airport> airports = query.getResultList();
            transaction.commit();
            return airports;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    public void addFlightToAirportById(Long airportId, Long flightId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Airport airport = session.find(Airport.class, airportId);
            Flight flight = session.find(Flight.class, flightId);

            if (airport != null && flight != null) {
                airport.getFlights().add(flight);
                session.update(airport);
                transaction.commit();
            } else {
                System.out.println("Airport or Flight not found.");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteFlightToAirportById(Long airportId, Long flightId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Airport airport = session.find(Airport.class, airportId);
            Flight flight = session.find(Flight.class, flightId);

            if (airport != null && flight != null) {
                airport.getFlights().remove(flight);
                session.update(airport);
                transaction.commit();
            } else {
                System.out.println("Airport or Flight not found.");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public boolean existsById(Long id) {
        return true;
    }
}

