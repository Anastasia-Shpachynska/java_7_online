package service.Impl;

import com.opencsv.CSVWriter;
import dao.Impl.TransactionDaoImpl;
import dao.TransactionDao;
import entity.Transaction;
import service.TransactionService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao = new TransactionDaoImpl();
    private static final String FILE_PATH = "TransactionHistory/receipt.csv";
    String[] headers = {"№", "Amount", "Sender", "Recipient", "Date"};
    @Override
    public void create(Transaction transaction) {
        transactionDao.create(transaction);
    }

    @Override
    public void findTransactionByNumber(Long idAccount, Long idTransaction) {
        Transaction transaction = transactionDao.findTransactionByNumber(idTransaction);
        if(transaction != null){
           String[] transactionRow = structureReceipt(transaction, idAccount);

            List<String[]> transactionsMass = new ArrayList<>();

            transactionsMass.add(formattedRows(headers));
            transactionsMass.add(formattedRows(transactionRow));

            writeToCsv(FILE_PATH, transactionsMass);
        }else {
            System.out.println("No transaction found with this number.");
        }
    }

    @Override
    public void findTransactionByDate(Long idAccount, LocalDate startDate, LocalDate finishDate) {
        List<Transaction> transactions = transactionDao.findTransactionByDate(idAccount, startDate, finishDate);
        if(!transactions.isEmpty()) {
            float[] result = incomeAndExpenses(transactions, idAccount);
            String income = String.valueOf(result[0]);
            String expenses = String.valueOf(-result[1]);
            String[] incomeMessage = {"Amount of income: ", income};
            String[] expensesMessage = {"Amount of expenses: ", expenses};

            List<String[]> transactionsMass = new ArrayList<>();
            transactionsMass.add(formattedRows(headers));

            for (Transaction transaction : transactions) {
                String[] transactionRow = structureReceipt(transaction, idAccount);
                transactionsMass.add(formattedRows(transactionRow));
            }
            transactionsMass.add(incomeMessage);
            transactionsMass.add(expensesMessage);
            writeToCsv(FILE_PATH, transactionsMass);
        }else {
            System.out.println("Transaction history is empty.");
        }
    }

    @Override
    public void findAllTransactionByAccount(Long idAccount) {
        List<Transaction> transactions = transactionDao.findAllTransactionByAccount(idAccount);
        if(!transactions.isEmpty()) {
            float[] result = incomeAndExpenses(transactions, idAccount);
            String income = String.valueOf(result[0]);
            String expenses = String.valueOf(-result[1]);
            String[] incomeMessage = {"Amount of income: ", income};
            String[] expensesMessage = {"Amount of expenses: ", expenses};

            List<String[]> transactionsMass = new ArrayList<>();
            transactionsMass.add(formattedRows(headers));

            for (Transaction transaction : transactions) {
                String[] transactionRow = structureReceipt(transaction, idAccount);
                transactionsMass.add(formattedRows(transactionRow));
            }
            transactionsMass.add(incomeMessage);
            transactionsMass.add(expensesMessage);
            writeToCsv(FILE_PATH, transactionsMass);
        }else {
            System.out.println("Transaction history is empty.");
        }
    }

    @Override
    public void writeToCsv(String filePath, List<String[]> transactions) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(transactions);
            System.out.println("Receipt -> " + new File(filePath).getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    private String[] formattedRows(String[] rows) {
        int columnWidth = 12;
        String formatString = "%-" + columnWidth + "s";

        String[] formattedRow = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
            formattedRow[i] = String.format(formatString, rows[i]);
        }
        return formattedRow;
    }

    private float[] incomeAndExpenses(List<Transaction> transactions, Long idAccount) {
        float income = 0;
        float expenses = 0;
        for (Transaction transaction : transactions) {
            if(idAccount.equals(transaction.getRecipient().getId())) {
                income += transaction.getAmount();
            } else if (idAccount.equals(transaction.getSender().getId())) {
                expenses += transaction.getAmount();
            }
        }
        return new float[]{income, expenses};
    }

    private String[] structureReceipt(Transaction transaction, Long idAccount) {
        float modifiedAmount = 0;
        if(idAccount.equals(transaction.getSender().getId())) {
            modifiedAmount = -transaction.getAmount();
        } else if (idAccount.equals(transaction.getRecipient().getId())) {
            modifiedAmount = transaction.getAmount();
        }
        String[] transactionRow = new String[]{
                transaction.getId().toString(),
                String.valueOf(modifiedAmount),
                String.valueOf(transaction.getSender().getAccount()),
                String.valueOf(transaction.getRecipient().getAccount()),
                formatDate(transaction.getDate())
        };
        return transactionRow;
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
