package controller;

import entity.Account;
import entity.Transaction;
import entity.User;
import service.AccountService;
import service.Impl.AccountServiceImpl;
import service.Impl.TransactionServiceImpl;
import service.Impl.UserServiceImpl;
import service.TransactionService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class Controller {

    private final UserService userService = new UserServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();
    private  final TransactionService transactionService = new TransactionServiceImpl();

    public void start() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            menu();
            String select;
            for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
                select(select, bufferedReader);
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void menu() {
        System.out.println("\n" + "Please, select an option." + "\n");
        System.out.println("Create new user and account- 1");
        System.out.println("Update user - 2");
        System.out.println("Delete the user and their account(s) - 3");
        System.out.println("Find the user and account(s) - 4");
        System.out.println("Find all users - 5" + "\n");
        System.out.println("Create new account - 6");
        System.out.println("Delete the account - 7");
        System.out.println("Find the account - 8");
        System.out.println("Find all accounts - 9" + "\n");
        System.out.println("Create a transaction - 10");
        System.out.println("Transaction history (csv) - 11");
        System.out.println("Close application - 12");
    }

    private void select(String select, BufferedReader bufferedReader) {
        switch (select) {
            case "1" -> createUser(bufferedReader);
            case "2" -> updateUser(bufferedReader);
            case "3" -> deleteUser(bufferedReader);
            case "4" -> findUserById(bufferedReader);
            case "5" -> findAllUsers();
            case "6" -> createAccount(bufferedReader);
            case "7" -> deleteAccount(bufferedReader);
            case "8" -> findAccountByNum(bufferedReader);
            case "9" -> findAllAccounts();
            case "10" -> createTransaction(bufferedReader);
            case "11" -> selectTransactionHistory(bufferedReader);
            case "12" -> System.exit(0);
            default -> System.out.println("Invalid option.");
        }
    }

    private void createUser(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the full name: ");
            String fullName = bufferedReader.readLine();
            System.out.println("Please, enter the email: ");
            String email = bufferedReader.readLine();
            System.out.println("Please, enter the start balance: ");
            float balance = Float.parseFloat(bufferedReader.readLine());

            if(balance >= 0) {
                User user = new User();
                user.setFullName(fullName);
                user.setEmail(email);

                Account account = new Account();
                account.setUser(user);
                account.setBalance(balance);
                account.setAccount(accountService.randomNumber());

                userService.create(user);
                accountService.create(account);
                System.out.println("Your id -> " + user.getId());
                System.out.println("Your account number -> " + account.getAccount());
            }else {
                System.out.println("The balance cannot be less than zero!");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void updateUser(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter user id: ");
            User user = userService.findById(bufferedReader.readLine());
            Set<Account> accounts = user.getAccounts();
            if(user != null) {
                System.out.println(user);
                for (Account account : accounts) {
                    System.out.println(account);
                }
                System.out.println("Change the user`s personal data - 1" +"\n"
                        + "Delete user account - 2");
                String select = bufferedReader.readLine();
                switch (select) {
                    case "1" -> {
                        System.out.println("Enter new full name: ");
                        String newFullName = bufferedReader.readLine();
                        System.out.println("Enter new email: ");
                        String newEmail = bufferedReader.readLine();

                        user.setFullName(newFullName);
                        user.setEmail(newEmail);
                        userService.update(user);
                    }
                    case "2" -> deleteAccount(bufferedReader);
                    default -> System.out.println("Invalid option.");
                }
            }else {
                System.out.println("User not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void deleteUser(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter user id: ");
            String userId = bufferedReader.readLine();
            User user = userService.findById(userId);
            if(user != null) {
                System.out.println(user);
                userService.delete(Long.valueOf(userId));
            }else {
                System.out.println("User not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void findUserById(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter user id: ");
            User user = userService.findById(bufferedReader.readLine());
            if(user != null) {
                Set<Account> accounts = user.getAccounts();
                System.out.println(user);
                for (Account account : accounts) {
                    System.out.println(account);
                }
            }else {
                System.out.println("User not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void findAllUsers() {
        System.out.println("All users: ");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void createAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter the user ID for which the account is being created: ");
            String userId = bufferedReader.readLine();
            User user = userService.findById(userId);
            if(user != null) {
                System.out.println("Please, enter the start balance: ");
                float balance = Float.parseFloat(bufferedReader.readLine());

                if(balance >= 0) {
                    Account account = new Account();
                    account.setUser(user);
                    account.setBalance(balance);
                    account.setAccount(accountService.randomNumber());

                    accountService.create(account);
                    System.out.println("Your account number -> " + account.getAccount());
                }else {
                    System.out.println("The balance cannot be less than zero!");
                }
            }else {
                System.out.println("User not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void deleteAccount(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter account number: ");
            String accountNum = bufferedReader.readLine();
            Account account = accountService.findByNum(accountNum);
            if(account != null) {
                System.out.println(account);
                accountService.delete(accountNum);
            }else {
                System.out.println("Account not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void findAccountByNum(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter account number: ");
            Account account = accountService.findByNum(bufferedReader.readLine());
            if(account != null) {
                System.out.println(account);
                System.out.println(account.getUser());
            }else {
                System.out.println("Account not found.");
            }
        } catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void findAllAccounts() {
        System.out.println("All accounts: ");
        List<Account> accounts = accountService.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private void createTransaction(BufferedReader bufferedReader) {
        try {
            boolean foundMatches = false;
            System.out.println("Please, enter your user id: ");
            User user = userService.findById(bufferedReader.readLine());
            if(user != null) {
                Set<Account> accounts = user.getAccounts();
                System.out.println("Please, enter account number: ");
                Account accountSender = accountService.findByNum(bufferedReader.readLine());
                if(accountSender != null) {
                    for (Account accountUser : accounts) {
                        if(accountUser.getAccount() == accountSender.getAccount()) {
                            foundMatches = true;
                            System.out.println("Please, enter the recipient's account: ");
                            Account accountRecipient = accountService.findByNum(bufferedReader.readLine());
                            if(accountRecipient != null) {
                                System.out.println(accountRecipient.getUser());
                                System.out.println("Please, enter the amount: ");
                                float amount = Float.parseFloat(bufferedReader.readLine());
                                if(amount > 0) {
                                    if(amount <= accountSender.getBalance()) {
                                        Transaction transaction = new Transaction();
                                        transaction.setSender(accountSender);
                                        transaction.setRecipient(accountRecipient);
                                        transaction.setAmount(amount);

                                        transactionService.create(transaction);
                                        float newSenderBalance = accountSender.getBalance() - amount;
                                        float newRecipientBalance = accountRecipient.getBalance() + amount;
                                        accountSender.setBalance(newSenderBalance);
                                        accountRecipient.setBalance(newRecipientBalance);
                                        accountService.update(accountSender);
                                        accountService.update(accountRecipient);
                                        System.out.println(transaction);
                                        System.out.println("Your balance: " + accountSender.getBalance());
                                    }else {
                                        System.out.println("Your account does not have enough funds.");
                                    }
                                }else {
                                    System.out.println("The transfer amount must be greater than 0.");
                                }
                            }else {
                                System.out.println("Account not found.");
                            }
                        } else if (!foundMatches) {
                            System.out.println("You do not have such an account.");
                        }
                    }
                }else {
                    System.out.println("Account not found.");
                }
            }else {
                System.out.println("User not found.");
            }
        } catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }catch (NumberFormatException ex) {
            System.out.println("Incorrect data format. " + ex.getMessage());
        }
    }

    private void transactionHistoryMenu() {
        System.out.println("\n" + "Find the transaction by transaction number - 1");
        System.out.println("History of transactions for a certain period of time - 2");
        System.out.println("Find all account transactions - 3");
        System.out.println("Return to the main menu - 4");
    }

    private void selectTransactionHistory(BufferedReader bufferedReader) {
        try {
            transactionHistoryMenu();
            String select;
            for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
                switch (select) {
                    case "1" -> findTransactionByNumber(bufferedReader);
                    case "2" -> findTransactionByDate(bufferedReader);
                    case "3" -> findAllTransactionByAccount(bufferedReader);
                    case "4" -> menu();
                    default -> System.out.println("Invalid option.");
                }
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void findTransactionByNumber(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter your account number: ");
            Account account = accountService.findByNum(bufferedReader.readLine());
            if(account != null) {
                System.out.println("Please, enter the transaction number: ");
                Long transactionNumber = Long.valueOf(bufferedReader.readLine());

                transactionService.findTransactionByNumber(account.getId(), transactionNumber);
            }else {
                System.out.println("Account not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }catch (NumberFormatException ex) {
            System.out.println("Incorrect receipt number format. The receipt number must contain only numbers. " + ex.getMessage());
        }
    }

    private void findTransactionByDate(BufferedReader bufferedReader) {
        try {
            boolean foundMatches = false;
            System.out.println("Please, enter your user id: ");
            User user = userService.findById(bufferedReader.readLine());
            if(user != null) {
                System.out.println("Please, enter your account number: ");
                Account account = accountService.findByNum(bufferedReader.readLine());
                for (Account userAccount : user.getAccounts()) {
                    if(userAccount.getId().equals(account.getId())) {
                        foundMatches = true;
                        System.out.println("Please, enter the date from which you want to start (YYYY-MM-DD): ");
                        String startDate = bufferedReader.readLine();
                        LocalDate startLocalDate = parseDate(startDate);

                        System.out.println("Please, enter the date on which you want to finish (YYYY-MM-DD): ");
                        String finishDate = bufferedReader.readLine();
                        LocalDate finishLocalDate = parseDate(finishDate);

                        if (startLocalDate != null && finishLocalDate != null) {
                            transactionService.findTransactionByDate(account.getId(), startLocalDate, finishLocalDate);
                        } else {
                            System.out.println("Invalid date format. Please enter dates in the format YYYY-MM-DD.");
                        }
                    } else if (!foundMatches) {
                        System.out.println("No account with this number was found for the user you specified.");
                    }
                }
            }else {
                System.out.println("User not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }
    private void findAllTransactionByAccount(BufferedReader bufferedReader) {
        try {
            boolean foundMatches = false;
            System.out.println("Please, enter your user id: ");
            User user = userService.findById(bufferedReader.readLine());
            if(user != null) {
                System.out.println("Please, enter your account number: ");
                Account account = accountService.findByNum(bufferedReader.readLine());
                for (Account userAccount : user.getAccounts()) {
                    if(userAccount.getId().equals(account.getId())) {
                        foundMatches = true;
                        transactionService.findAllTransactionByAccount(account.getId());
                    } else if (!foundMatches) {
                        System.out.println("No account with this number was found for the user you specified.");
                    }
                }
            }else {
                System.out.println("User not found.");
            }
        }catch (IOException ex) {
            System.out.println("An error occurred while reading data. Please check them and try again.");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static LocalDate parseDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return null;
        }
    }
}
