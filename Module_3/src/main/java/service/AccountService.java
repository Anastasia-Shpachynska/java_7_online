package service;

import entity.Account;

public interface AccountService extends CRUDService<Account> {
    void delete(String accountNum);
    Account findByNum(String accountNum);
    int randomNumber();
}
