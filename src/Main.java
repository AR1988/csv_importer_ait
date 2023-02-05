import db.MockDb;
import entity.BankAcc;
import entity.Order;
import entity.User;
import mapper.BankAccMapper;
import mapper.UserMapper;
import model.CsvRowModel;
import reader.CsvReader;
import service.BankAkkService;
import service.OrderService;
import service.UserService;

import java.util.*;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class Main {
    private static final int IBAN_LENGTH = 14;
    private static final String KONTO_NR_KEY = "KontoNr";
    private static final String BANK_ID_KEY = "BankId";
    private static final int KONTO_LENGTH = 8;

    static MockDb mockDb = new MockDb();
    static BankAkkService bankAkkService = new BankAkkService(mockDb);
    static OrderService orderService = new OrderService(mockDb);
    static UserService userService = new UserService(mockDb);

    public static void main(String[] args) {
//        ArrayList<String> file = CsvReader.readFile();
        List<List<String>> files = CsvReader.readFiles();

        String fileName = "File";
        for (List<String> file : files) {
            Order order = new Order();
            orderService.save(order);
            List<CsvRowModel> csvRowsModels = new LinkedList<>();
            for (int rowNumber = 1; rowNumber < file.size() + 1; rowNumber++) {
                String[] columnsOfRowAsArray = file.get(rowNumber - 1).split(";");
//                List<String> columnsOfRow = List.of(columnsOfRowAsArray);
                List<String> columnsOfRow = Arrays.asList(columnsOfRowAsArray);
                ArrayList<String> errors = new ArrayList<>();
                if (columnsOfRow.size() != KONTO_LENGTH) {
                    errors.add("Line: " + rowNumber + ". Error: Number of columns is not correct");
                }
                CsvRowModel rowModel = new CsvRowModel(columnsOfRow, errors, rowNumber);
                csvRowsModels.add(rowModel);
                if (!errors.isEmpty()) {
                    continue;
                }
                //todo fix it
                Map<String, Long> kontoDetails = getKontoDetails(rowModel);
                if (!errors.isEmpty() || kontoDetails == null) {
                    continue;
                }
                Long kontoNr = kontoDetails.get(KONTO_NR_KEY);
                Long bankId = kontoDetails.get(BANK_ID_KEY);
                BankAcc bankAcc = BankAccMapper.mapFromCsvModelToBankAcc(rowModel, kontoNr, bankId, order);
                User user = UserMapper.mapFromCsvModelToUser(rowModel, order);
                user.setBankAccId(bankAcc.getId());
                bankAcc.setUserId(user.getId());

                bankAkkService.save(bankAcc);
                userService.save(user);
            }
            System.out.println("File " + fileName + "_" + order.getId());
            for (CsvRowModel csvRowsModel : csvRowsModels) {
                System.out.println(csvRowsModel.printReportMessage());
            }
        }
    }

    private static Map<String, Long> getKontoDetails(CsvRowModel rowModel) {
        //      iban =  50010094569875
        String ibanAsString = String.valueOf(rowModel.getIban());
        if (ibanAsString.length() != IBAN_LENGTH) {
            List<String> errors = rowModel.getErrors();
            errors.add("LineNr: " + rowModel.getRowNumber() + ". Iban length is not allowed. Expected length: "
                    + IBAN_LENGTH + ", Actually Length: " + ibanAsString.length());
            return null;
        }
        //      ibanAsString =  "50010094569875"
        String kontoNrAsString = ibanAsString.substring(0, KONTO_LENGTH);
        //      ibanAsString    =  "50010094569875"
        //      kontoNrAsString =  "50010094"
        String bankIdAsString = ibanAsString.substring(KONTO_LENGTH);
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

    private static void printErrors(ArrayList<String> errors) {
        System.out.println(errors);
    }
}


//0	FirstName	User
//1	LastName	User
//2	Address	User
//3	INDEX	User
//4	TEL	User

//5	IBAN	BankAcc
//6	Balance	BankAcc
//7	CREDIT	Tspe        C
