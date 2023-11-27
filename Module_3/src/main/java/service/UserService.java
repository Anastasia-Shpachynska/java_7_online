package service;

import entity.User;

public interface UserService extends CRUDService<User> {
    void delete(Long id);
    User findById(String id);
}
