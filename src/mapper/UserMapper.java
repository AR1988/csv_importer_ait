package mapper;

import entity.Order;
import entity.User;
import model.CsvRowModel;

/**
 * @author Andrej Reutow
 * created on 04.02.2023
 */
public class UserMapper {

    public static User mapFromCsvModelToUser(CsvRowModel rowModel, Order order) {
        return new User(rowModel.getName(), rowModel.getLastName(), rowModel.getAddress(), rowModel.getCityCode(), rowModel.getPhoneNr(), order.getId());
    }
}
