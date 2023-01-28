package db;

import entity.BankAcc;
import entity.Order;
import entity.User;

/**
 * @author Andrej Reutow
 * created on 28.01.2023
 */
public interface IMockDb {

    void persist(User user);

    void persist(Order user);

    void persist(BankAcc user);

    User getUser(long id);

    Order getOrder(long id);

    BankAcc getBankAcc(long id);
}
