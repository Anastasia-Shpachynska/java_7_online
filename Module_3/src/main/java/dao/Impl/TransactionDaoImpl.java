package dao.Impl;

import config.HibernateConfig;
import dao.TransactionDao;
import entity.Transaction;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Transaction transactionCreate) {
        org.hibernate.Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(transactionCreate);
            transaction.commit();
            System.out.println("The transaction is successful.");
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Transaction findTransactionByNumber(Long idTransaction) {
        org.hibernate.Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Transaction transactionResult = session.find(Transaction.class, idTransaction);
            transaction.commit();
            return transactionResult;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Transaction> findTransactionByDate(Long idAccount, LocalDate startDate, LocalDate finishDate) {
        org.hibernate.Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select t from Transaction t where (t.sender.id = :idAccount or t.recipient.id = :idAccount) and t.date between :startDate and :finishDate", Transaction.class);
            query.setParameter("idAccount", idAccount);
            query.setParameter("startDate", startDate.atStartOfDay());
            query.setParameter("finishDate", finishDate.plusDays(1).atStartOfDay());
            List<Transaction> transactions = query.getResultList();
            transaction.commit();
            return transactions;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<Transaction> findAllTransactionByAccount(Long idAccount) {
        org.hibernate.Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select t from Transaction t", Transaction.class);
            List<Transaction> transactions = query.getResultList();
            transaction.commit();
            return transactions;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }
}
