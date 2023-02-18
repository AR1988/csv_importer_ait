package parser;

import entity.Type;

import java.util.List;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class Parser {

    public static final String COLUMN = "Колонка: ";

    public static String parseToString(String value, String columnName, boolean require, List<String> errors) {
        boolean isAllowed = checkRequire(value, columnName, require, errors);
        if (!isAllowed) {
            errors.add(getErrorHeader(columnName) + "Value " + value + "is not allowed");
            return null;
        }
        return value;
    }

    public static Integer parseToInt(String value, String columnName, boolean require, List<String> errors) {
        boolean isValid = checkRequire(value, columnName, require, errors);
        if (!isValid) {
            return null;
        }
        try {
            //a012
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            errors.add(getErrorHeader(columnName) + "Value " + value + "is not a number");
            return null;
        }
    }

    public static Long parseToLong(String value, String columnName, boolean require, List<String> errors) {
        boolean isValid = checkRequire(value, columnName, require, errors);
        if (!isValid) {
            errors.add(getErrorHeader(columnName) + "Value " + value + "is not allowed");
            return null;
        }
        try {
            if (!value.isBlank()) {
                return Long.parseLong(value);
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            errors.add(getErrorHeader(columnName) + "Value " + value + " is not a number");
            return null;
        }
    }

    public static Double parseToDouble(String value, String columnName, boolean require, List<String> errors) {
        boolean isValid = checkRequire(value, columnName, require, errors);
        if (!isValid) {
            errors.add(getErrorHeader(columnName) + "Value " + value + "is not allowed");
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            errors.add(getErrorHeader(columnName) + "Value " + value + " is not a (dezimal) number");
            return null;
        } catch (NullPointerException e) {
            errors.add(getErrorHeader(columnName) + "Can not parse to double blank String: " + value);
            return null;
        }
    }

    public static Type parseTypeEnum(String code, String columnName, boolean require, List<String> errors) {
        if (code == null) {
            errors.add(getErrorHeader(columnName) + " Value code is null");
            return null;
        }
        boolean isAllowed = checkRequire(code, columnName, require, errors);
        if (!isAllowed) {
            errors.add(getErrorHeader(columnName) + "Value " + code + "is not allowed");
            return null;
        }
        Type[] enumValues = Type.values();
        Type resultType = null;
        for (Type enumValue : enumValues) {
            if (enumValue.getCode().equals(code)) {
                resultType = enumValue;
            }
        }
        if (resultType == null) {
            errors.add(getErrorHeader(columnName) + "Type with " + code + "is not exist");
        }
        return resultType;
    }

    private static boolean checkRequire(String value, String columnName, boolean require, List<String> errors) {
        if (require) {
            if (value.isBlank()) {
                errors.add(getErrorHeader(columnName) + "Value " + value + "is not allowed");
                return false;
            }
        }
        return true;
    }

    public static String getErrorHeader(String columnName) {
        return COLUMN + columnName;
    }
}
