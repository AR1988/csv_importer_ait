package service;

import db.MockDb;
import entity.Order;
import entity.OrderStatus;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<User> getAll() {
        return db.getUsers();
    }

    public Map<Order, List<User>> groupAllByOrder(Order order) {
        List<User> users = db.getUsers();
        Map<Order, List<User>> orderMap = new HashMap<>();

        if (order == null || !order.getOrderStatus().equals(OrderStatus.VALID)) {
            orderMap.put(order, new ArrayList<>());
            return orderMap;
        }

        for (User user : users) {
            if (user.getOrder() != null && user.getOrder().equals(order)) {
                List<User> defaultList = orderMap.getOrDefault(order, new ArrayList<>());
                defaultList.add(user);
                orderMap.put(order, defaultList);
            }
        }
        return orderMap;
    }

    public Map<Order, List<User>> groupAllByOrder() {
        Map<Order, List<User>> orderMap = new HashMap<>();
        //todo сгруппировать список user в HashMap по всем доступным Order.
        // Статус Order (OrderStatus) должен быть строго только OrderStatus.VALID
        return orderMap;
    }

//    public Map<Order, List<User>> groupAllByOrder() {
//        Map<Order, List<User>> orderMap = new HashMap<>();
//
//        Map<OrderStatus, Map<Order, List<User>>> statusMap = new HashMap<>();
//        todo сгруппировать список user в HashMap по всем доступным Order.
//         Статус Order (OrderStatus) должен быть строго только OrderStatus.INVALID
//        return orderMap;
//    }
}
