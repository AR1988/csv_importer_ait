package mapper;

import entity.BankAcc;
import entity.Order;
import model.CsvRowModel;

/**
 * @author Andrej Reutow
 * created on 04.02.2023
 */
public class BankAccMapper {

    public static BankAcc mapFromCsvModelToBankAcc(CsvRowModel rowModel, Long kontoNr, Long bankId, Order order) {
        return new BankAcc(kontoNr, bankId, rowModel.getBalance(), order.getId(), rowModel.getTypeCode());
    }
}
