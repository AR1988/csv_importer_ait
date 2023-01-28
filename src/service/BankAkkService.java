package service;

import db.MockDb;
import entity.BankAcc;

/**
 * @author Andrej Reutow
 * created on 28.01.2023
 */
public class BankAkkService implements IService<BankAcc> {

    private MockDb db;

    public BankAkkService(MockDb db) {
        this.db = db;
    }

    @Override
    public void save(BankAcc bankAcc) {
        db.persist(bankAcc);
    }

    @Override
    public BankAcc get(long id) {
        return db.getBankAcc(id);
    }
}
