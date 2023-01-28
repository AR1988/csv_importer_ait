package service;

import db.MockDb;
import entity.User;

/**
 * @author Andrej Reutow
 * created on 28.01.2023
 */
public class UserService implements IService<User> {

    private MockDb db;

    public UserService(MockDb db) {
        this.db = db;
    }

    @Override
    public void save(User entity) {
        db.persist(entity);
    }

    @Override
    public User get(long id) {
        return db.getUser(id);
    }
}
