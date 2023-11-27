package dao.Impl;

import config.HibernateConfig;
import dao.UserDao;
import entity.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(User entity) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            System.out.println("User has been created.");
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(User entity) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            System.out.println("User has been updated.");
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User has been deleted.");
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public User findById(Long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            transaction.commit();
            return user;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select e from User e");
            List<User> users = query.getResultList();
            transaction.commit();
            return users;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }
}
