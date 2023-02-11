package db;

import entity.BankAcc;
import entity.Order;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrej Reutow
 * created on 28.01.2023
 */
public class MockDb implements IMockDb {

    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<BankAcc> bankAccs = new ArrayList<>();

    @Override
    public void persist(User user) {
        this.users.add(user);
    }

    @Override
    public void persist(Order order) {
        this.orders.add(order);
    }

    @Override
    public void persist(BankAcc bankAcc) {
        this.bankAccs.add(bankAcc);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<BankAcc> getBankAccs() {
        return bankAccs;
    }

    @Override
    public User getUser(long id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Order getOrder(long id) {
        for (Order order : this.orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public BankAcc getBankAcc(long id) {
        for (BankAcc bankAcc : this.bankAccs) {
            if (bankAcc.getId() == id) {
                return bankAcc;
            }
        }
        return null;
    }
}
