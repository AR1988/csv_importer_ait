package model;

/**
 * @author Andrej Reutow
 * created on 18.02.2023
 */
public enum CsvColumnEnum {

    FIRST_NAME("FirstName", 0),
    LAST_NAME("LastName", 1),
    ADDRESS("Address", 2),
    INDEX("Index", 3),
    TEL("TEL", 4),
    IBAN("IBAN", 5),
    BALANCE("Balance", 6),
    TYPE_CODE("CREDIT", 7);
    private final String columnName;
    private final int columnIndex;

    CsvColumnEnum(String columnName, int columnIndex) {
        this.columnName = columnName;
        this.columnIndex = columnIndex;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
