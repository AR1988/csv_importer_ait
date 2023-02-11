package service;

import db.MockDb;
import entity.BankAcc;

import java.util.List;

/**
 * @author Andrej Reutow
 * created on 28.01.2023
 */
public class BankAccService implements IService<BankAcc> {

    private MockDb db;

    public BankAccService(MockDb db) {
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

    @Override
    public List<BankAcc> getAll() {
        return db.getBankAccs();
    }
}
