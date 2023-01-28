package entity;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public enum Type {
    CREDIT("C", 1), DEBIT("D", 2);

    private String code;
    private int id;

    Type(String code, int id) {
        this.code = code;
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    //TODO метод для поиска соответствий
}
