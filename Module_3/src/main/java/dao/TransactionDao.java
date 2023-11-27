package dao;

import entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionDao {
    void create(Transaction transaction);
    Transaction findTransactionByNumber(Long idTransaction);
    List<Transaction> findTransactionByDate(Long idAccount, LocalDate startDate, LocalDate finishDate);
    List<Transaction> findAllTransactionByAccount(Long idAccount);
}
