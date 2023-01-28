package service;

import db.MockDb;
import entity.Order;

/**
 * @author Andrej Reutow
 * created on 28.01.2023
 */
public class OrderService implements IService<Order> {

    private MockDb db;

    public OrderService(MockDb db) {
        this.db = db;
    }

    @Override
    public void save(Order entity) {
        db.persist(entity);
    }

    @Override
    public Order get(long id) {
        return db.getOrder(id);
    }
}
