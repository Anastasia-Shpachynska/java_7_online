package service.Impl;

import dao.AccountDao;
import dao.Impl.AccountDaoImpl;
import entity.Account;
import service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void create(Account entity) {
        accountDao.create(entity);
    }

    @Override
    public void update(Account entity) {
        accountDao.update(entity);
    }

    @Override
    public void delete(String accountNum) {
        accountDao.delete(Integer.parseInt(accountNum));
    }

    @Override
    public Account findByNum(String accountNum) {
        isEmpty(String.valueOf(accountNum));
        if(!accountNum.matches("\\d{10}")) {
            System.out.println("Incorrect account number! The account number must consist of 10 digits.");
            throw new RuntimeException();
        }
        long accountNumber = Long.parseLong(accountNum);
        if(accountNumber > 2_147_483_647) {
            System.out.println("Such an account number does not exist.");
            throw new RuntimeException();
        }
        return accountDao.findByNum((int) accountNumber);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public void isEmpty(String data) {
        if(data.isEmpty()) {
            System.out.println("Field can not be empty!");
            throw new RuntimeException();
        }
    }

    @Override
    public int randomNumber() {
        return accountDao.randomNumber();
    }
}
