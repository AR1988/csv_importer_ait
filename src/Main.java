import db.MockDb;
import entity.BankAcc;
import entity.Order;
import entity.User;
import file_helper.FileHelper;
import mapper.BankAccMapper;
import mapper.UserMapper;
import model.CsvRowModel;
import parser.Parser;
import service.BankAccService;
import service.OrderService;
import service.UserService;

import java.util.*;

import static entity.OrderStatus.INVALID;
import static entity.OrderStatus.VALID;
import static model.CsvColumnEnum.IBAN;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class Main {
    private static final int IBAN_LENGTH = 14;
    private static final String KONTO_NR_KEY = "KontoNr";
    private static final String BANK_ID_KEY = "BankId";
    private static final int COLUMN_LENGTH = 8;

    static MockDb mockDb = new MockDb();
    static BankAccService bankAccService = new BankAccService(mockDb);
    static OrderService orderService = new OrderService(mockDb);
    static UserService userService = new UserService(mockDb);

    public static void main(String[] args) {
        List<String> strings = FileHelper.readFile("test.txt");
        Order order = new Order();
        orderService.save(order);
        List<CsvRowModel> csvRowsModels = new LinkedList<>();
        List<User> successfulSavedUsers = new ArrayList<>();
        for (int rowNumber = 1; rowNumber < strings.size() + 1; rowNumber++) {
            ArrayList<String> errors = new ArrayList<>();
            try {
                String[] columnsOfRowAsArray = strings.get(rowNumber - 1).split(";");
                List<String> columnsOfRow = Arrays.asList(columnsOfRowAsArray);
                if (columnsOfRow.size() != COLUMN_LENGTH) {
                    order.setOrderStatus(INVALID);
                    errors.add("Error: Number of columns is not correct");
                    CsvRowModel rowModel = new CsvRowModel(errors, rowNumber);
                    csvRowsModels.add(rowModel);
                    continue;
                }
                CsvRowModel rowModel = new CsvRowModel(columnsOfRow, errors, rowNumber);
                csvRowsModels.add(rowModel);
                if (!errors.isEmpty()) {
                    order.setOrderStatus(INVALID);
                    continue;
                }
                //todo fix it
                Map<String, Long> kontoDetails = getKontoDetails(rowModel);
                if (!errors.isEmpty() || kontoDetails == null) {
                    order.setOrderStatus(INVALID);
                    continue;
                }
                Long kontoNr = kontoDetails.get(KONTO_NR_KEY);
                Long bankId = kontoDetails.get(BANK_ID_KEY);
                User user = UserMapper.mapFromCsvModelToUser(rowModel, order);
                BankAcc bankAcc = BankAccMapper.mapFromCsvModelToBankAcc(rowModel, user, kontoNr, bankId, order);
                user.addBankAcc(bankAcc);

                bankAccService.save(bankAcc);
                userService.save(user);
                successfulSavedUsers.add(user);
            } catch (Exception e) {
                String msg = "Unexpected error";
                order.setOrderStatus(INVALID);
                boolean isFound = false;
                for (CsvRowModel csvRowsModel : csvRowsModels) {
                    if (csvRowsModel.getRowNumber() == rowNumber) {
                        csvRowsModel.getErrors().add(msg);
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                    errors.add(msg);
                    CsvRowModel rowModel = new CsvRowModel(errors, rowNumber);
                    csvRowsModels.add(rowModel);
                }
            }
            if (successfulSavedUsers.size() == csvRowsModels.size()) {
                order.setOrderStatus(VALID);
            }
        }
        List<String> reportList = new ArrayList<>();
        for (CsvRowModel csvRowsModel : csvRowsModels) {
            System.out.println(csvRowsModel.printReportMessage());
            reportList.add(csvRowsModel.printReportMessage());
            FileHelper.writeToFile(reportList, "report_test1");
        }
    }

    private static Map<String, Long> getKontoDetails(CsvRowModel rowModel) {
        //      iban =  50010094569875
        String ibanAsString = String.valueOf(rowModel.getIban());
        if (ibanAsString.length() != IBAN_LENGTH) {
            List<String> errors = rowModel.getErrors();
            errors.add(Parser.getErrorHeader(IBAN.getColumnName()) + "Iban length is not allowed. Expected length: "
                    + IBAN_LENGTH + ", Actually Length: " + ibanAsString.length());
            return null;
        }
        //      ibanAsString =  "50010094569875"
        String kontoNrAsString = ibanAsString.substring(0, 8);
        //      ibanAsString    =  "50010094569875"
        //      kontoNrAsString =  "50010094"
        String bankIdAsString = ibanAsString.substring(8);
        //      ibanAsString    =  "50010094569875"
        //      kontoNrAsString =  "50010094"
        //      bankIdAsString =  "569875"

        Long kontoNr = Long.parseLong(kontoNrAsString);
        Long banId = Long.parseLong(bankIdAsString);
        //     long kontoNr =  50010094
        //     long banId =  569875
        Map<String, Long> result = new HashMap<>();
        result.put(KONTO_NR_KEY, kontoNr);
        result.put(BANK_ID_KEY, banId);
        return result;
    }
}
