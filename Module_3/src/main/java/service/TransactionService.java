package service;

import entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    void create(Transaction transaction);
    void findTransactionByNumber(Long idAccount, Long idTransaction);
    void findTransactionByDate(Long idAccount, LocalDate startDate, LocalDate finishDate);
    void findAllTransactionByAccount(Long idAccount);
    void writeToCsv(String filePath, List<String[]> transactions);
}
