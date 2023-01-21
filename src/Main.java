import entity.BankAcc;
import entity.Order;
import entity.Type;
import entity.User;
import parser.Parser;
import reader.CsvReader;

import java.util.ArrayList;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> rows = CsvReader.readFile();

        for (int lineNumber = 1; lineNumber < rows.size() + 1; lineNumber++) {
            String[] split = rows.get(lineNumber - 1).split(";");
            ArrayList<String> errors = new ArrayList<>();
            if (split.length != 8) {
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

            Long bankAccIban = Parser.parseToLong(iban, true, errors);
            Double bankAccBalance = Parser.parseToDouble(balance, true, errors);
            Type bankAccType = Parser.parseTypeEnum(typeCode, true, errors);

            if (!errors.isEmpty()) {
                printErrors(errors);
                continue;
            }

            Order order = new Order();
            BankAcc bankAcc = new BankAcc(bankAccIban, bankAccIban, bankAccBalance, order.getId(), bankAccType);
            User user = new User(userName, userLastName, userAddress, userCityCode, userPhoneNr, order.getId());
            user.setBankAccId(bankAcc.getId());
            bankAcc.setUserId(user.getId());
            System.out.println(order);
            System.out.println(user);
            System.out.println(bankAcc);
        }
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
