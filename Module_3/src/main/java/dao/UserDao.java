package dao;

import entity.User;

public interface UserDao extends CRUDDao<User> {
    void delete(Long id);
    User findById(Long id);
}
