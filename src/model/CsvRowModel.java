package model;

import entity.Type;
import parser.Parser;

import java.util.List;

/**
 * @author Andrej Reutow
 * created on 04.02.2023
 */
public class CsvRowModel {
    private final List<String> columnsOfRow;
    private final List<String> errors;
    private final int rowNumber;
    private String name;
    private String lastName;
    private String address;
    private Integer cityCode;
    private Long phoneNr;
    private Long iban;
    private Double balance;
    private Type typeCode;

    public CsvRowModel(List<String> columnsOfRow, List<String> errors, int rowNumber) {
        this.columnsOfRow = columnsOfRow;
        this.errors = errors;
        this.rowNumber = rowNumber;
        init();
    }

    public void init() {
        name = Parser.parseToString(columnsOfRow.get(0), true, errors);
        lastName = Parser.parseToString(columnsOfRow.get(1), false, errors);
        address = Parser.parseToString(columnsOfRow.get(2), true, errors);
        cityCode = Parser.parseToInt(columnsOfRow.get(3), true, errors);
        phoneNr = Parser.parseToLong(columnsOfRow.get(4), false, errors);
        iban = Parser.parseToLong(columnsOfRow.get(5), true, errors);
        balance = Parser.parseToDouble(columnsOfRow.get(6), true, errors);
        typeCode = Parser.parseTypeEnum(columnsOfRow.get(7), true, errors);
    }

    public String printReportMessage() {
        if (errors.isEmpty()) {
            return "Строка: " + rowNumber + " импортирована без ошибок";
        } else {
            StringBuilder builder = new StringBuilder();
            //rowNr -> 1
            //errors -> ["Parse Error", "some error"]
            for (int i = 0; i < errors.size(); i++) {
                String error = errors.get(i);
//                builder.append("Строка: ");
//                builder.append(rowNumber);
//                builder.append(", Ошибка ");
//                builder.append(i + 1);
//                builder.append(": ");
//                builder.append(error);
//                builder.append("\n");
                builder.append("Строка: ").append(rowNumber).append(", Ошибка ").append(i + 1).append(": ").append(error);
                if (i < errors.size() - 1) {
                    builder.append("\n");
                }
            }
            //Строка: 1, Error 1: Parse Error
            //Строка: 1, Error 2: some error
            return builder.toString();
        }
    }

    public List<String> getColumnsOfRow() {
        return columnsOfRow;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public Long getPhoneNr() {
        return phoneNr;
    }

    public Long getIban() {
        return iban;
    }

    public Double getBalance() {
        return balance;
    }

    public Type getTypeCode() {
        return typeCode;
    }
}

//                //User
//                String name = split[0];
//                String lastName = split[1];
//                String address = split[2];
//                String cityCode = split[3];
//                String phoneNr = split[4];
//                //BankAcc
//                String iban = split[5];
//                String balance = split[6];
//                String typeCode = split[7];  // C | D
//
//                String userName = Parser.parseToString(name, true, errors);
//                String userLastName = Parser.parseToString(lastName, true, errors);
//                String userAddress = Parser.parseToString(address, true, errors);
//                Integer userCityCode = Parser.parseToInt(cityCode, true, errors);
//                Long userPhoneNr = Parser.parseToLong(phoneNr, true, errors);
//
//                //todo fix it
//                Long bankAccIban = Parser.parseToLong(iban, true, errors);
//                Double bankAccBalance = Parser.parseToDouble(balance, true, errors);
//                Type bankAccType = Parser.parseTypeEnum(typeCode, true, errors);
