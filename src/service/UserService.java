package service;

import db.MockDb;
import entity.Order;
import entity.OrderStatus;
import entity.User;

import java.util.*;

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

    public Map<Order, List<User>> groupAllByOrder(OrderStatus status) {
        Map<Order, List<User>> orderMap = new HashMap<>();
        //todo сгруппировать список user в HashMap по всем доступным Order.
        // Статус Order (OrderStatus) должен быть строго только OrderStatus.VALID
        List<User> users = getAll();
        for (User user : users) {
            Order order = user.getOrder();
            OrderStatus orderStatus = order.getOrderStatus();
            if (null != status && status.equals(orderStatus)) {
                List<User> userList = orderMap.getOrDefault(order, new ArrayList<>());
                userList.add(user);
                orderMap.put(order, userList);
            }
        }
        return orderMap;
    }

//    public Map<Order, List<User>> groupOrderStatus() {
//        Map<Order, List<User>> orderMap = new HashMap<>();
//        //todo сгруппировать список user в HashMap по всем доступным Order.
//        // Статус Order (OrderStatus) должен быть строго только OrderStatus.VALID
//        List<User> users = getAll();
//        for (User user : users) {
//            Order order = user.getOrder();
//            List<User> userList = orderMap.getOrDefault(order, new ArrayList<>());
//            userList.add(user);
//            orderMap.put(order, userList);
//        }
//        return orderMap;
//    }


    public Map<OrderStatus, Map<Order, List<User>>> groupAllByOrderStatus() {
        Map<OrderStatus, Map<Order, List<User>>> statusMap = new HashMap<>();
//        todo сгруппировать список user в HashMap по всем доступным Order.
//        Статус Order (OrderStatus) должен быть строго только OrderStatus.INVALID
        List<OrderStatus> orderStatusList = Arrays.asList(OrderStatus.values());

        for (OrderStatus orderStatus : orderStatusList) {
            Map<Order, List<User>> orderMap = groupAllByOrder(orderStatus);
            statusMap.put(orderStatus, orderMap);
        }
        return statusMap;
    }
}
