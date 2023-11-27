package service.Impl;

import dao.Impl.UserDaoImpl;
import dao.UserDao;
import entity.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void create(User entity) {
        isEmpty(entity.getFullName());
        isEmpty(entity.getEmail());
        List<User> users = findAll();
        for (User user : users) {
            if(user.getEmail().equals(entity.getEmail())) {
                System.out.println("A user with this email already exists.");
                throw new RuntimeException();
            }
        }
        userDao.create(entity);
    }

    @Override
    public void update(User entity) {
        isEmpty(entity.getFullName());
        isEmpty(entity.getEmail());
        userDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);

    }

    @Override
    public User findById(String id) {
        isEmpty(id);
        if(!id.matches("\\d+")) {
            System.out.println("Incorrect user id. Id should consist only of numbers.");
            throw new RuntimeException();
        }
        Long idLong = Long.valueOf(id);
        return userDao.findById(idLong);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void isEmpty(String data) {
        if (data.isEmpty()) {
            System.out.println("Field can not be empty!");
            throw new RuntimeException();
        }
    }
}
