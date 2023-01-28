import db.MockDb;
import entity.BankAcc;
import entity.Order;
import entity.Type;
import entity.User;
import parser.Parser;
import reader.CsvReader;
import service.BankAkkService;
import service.OrderService;
import service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for (List<String> file : files) {
            Order order = null;
            for (int lineNumber = 1; lineNumber < file.size() + 1; lineNumber++) {
                String[] split = file.get(lineNumber - 1).split(";");
                ArrayList<String> errors = new ArrayList<>();

                if (split.length != KONTO_LENGTH) {
                    errors.add("Line: " + lineNumber + ". Error: Number of columns is not correct");
                }
//            if (errors.size() > 0) {
                if (!errors.isEmpty()) {
                    printErrors(errors);
                    continue;
                }
                //User
                String name = split[0];
                String lastName = split[1];
                String address = split[2];
                String cityCode = split[3];
                String phoneNr = split[4];
                //BankAcc
                String iban = split[5];
                String balance = split[6];
                String typeCode = split[7];  // C | D

                String userName = Parser.parseToString(name, true, errors);
                String userLastName = Parser.parseToString(lastName, true, errors);
                String userAddress = Parser.parseToString(address, true, errors);
                Integer userCityCode = Parser.parseToInt(cityCode, true, errors);
                Long userPhoneNr = Parser.parseToLong(phoneNr, true, errors);

                //todo fix it
                Long bankAccIban = Parser.parseToLong(iban, true, errors);
                Double bankAccBalance = Parser.parseToDouble(balance, true, errors);
                Type bankAccType = Parser.parseTypeEnum(typeCode, true, errors);

                if (!errors.isEmpty()) {
                    printErrors(errors);
                    continue;
                }
                //todo fix it
                Map<String, Long> kontoDetails = getKontoDetails(bankAccIban, errors, lineNumber);
                if (!errors.isEmpty() || kontoDetails == null) {
                    printErrors(errors);
                    continue;
                }

                if (order == null) {
                    order = new Order();
                }
                Long kontoNr = kontoDetails.get(KONTO_NR_KEY);
                Long bankId = kontoDetails.get(BANK_ID_KEY);
                BankAcc bankAcc = new BankAcc(kontoNr, bankId, bankAccBalance, order.getId(), bankAccType);
                User user = new User(userName, userLastName, userAddress, userCityCode, userPhoneNr, order.getId());
                user.setBankAccId(bankAcc.getId());
                bankAcc.setUserId(user.getId());

                bankAkkService.save(bankAcc);
                userService.save(user);
            }
            orderService.save(order);
        }
    }

    private static Map<String, Long> getKontoDetails(Long iban, List<String> errors, int lineNr) {
//      iban =  50010094569875
        String ibanAsString = String.valueOf(iban);
        if (ibanAsString.length() != IBAN_LENGTH) {
            errors.add("LineNr: " + lineNr + ". Iban length is not allowed. Expected length: " + IBAN_LENGTH + ", Actually Length: " + ibanAsString.length());
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


//0	FirtName	User
//1	LastName	User
//2	Address	User
//3	INDEX	User
//4	TEL	User

//5	IBAN	BankAcc
//6	Balance	BankAcc
//7	CREDIT	Tspe        C
