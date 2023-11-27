package dao;

import entity.Account;

public interface AccountDao extends CRUDDao<Account> {
    void delete(int accountNum);
    Account findByNum(int accountNum);
    int randomNumber();
    boolean uniqueNum(int accountNum);
}
