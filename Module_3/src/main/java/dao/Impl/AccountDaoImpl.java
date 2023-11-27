package dao.Impl;

import config.HibernateConfig;
import dao.AccountDao;
import entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AccountDaoImpl implements AccountDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Account entity) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            System.out.println("Account has been created.");
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void update(Account entity) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
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
    public void delete(int accountNum) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery("select id from Account where account = :accountNum", Long.class);
            query.setParameter("accountNum", accountNum);
            Long id = query.uniqueResult();
            Account account = session.find(Account.class, id);
            session.delete(account);
            transaction.commit();
            System.out.println("Account has been deleted.");
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Account findByNum(int accountNum) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery("select id from Account where account = :accountNum", Long.class);
            query.setParameter("accountNum", accountNum);
            Long id = query.uniqueResult();
            Account account = null;
            if(id != null) {
                account = session.find(Account.class, id);
            }
            transaction.commit();
            return account;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select e from Account e");
            List<Account> accounts = query.getResultList();
            transaction.commit();
            return accounts;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public int randomNumber() {
        Random random = new Random();
        int accountNum;
        accountNum = 1_000_000_000 + random.nextInt(2_147_483_647 - 1_000_000_000 + 1);
        boolean result = uniqueNum(accountNum);
        if(!result) {
           randomNumber();
        }
        return accountNum;
    }

    @Override
    public boolean uniqueNum(int accountNum) {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery("select count (*) from Account where account = :accountNum", Long.class);
            query.setParameter("accountNum", accountNum);
            Long count = query.uniqueResult();
            if(count == 0) {
                return true;
            }
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }
}
