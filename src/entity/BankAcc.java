package entity;

import java.util.Objects;
/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class BankAcc {

    private static Long idCounter = 0L;
    //50010094569875
    private final Long id;
    private Long kontoNr;
    private Long bankId;
    private Double balance;
    private Order order;
    private User user;
    private Type typeEnum;

    public BankAcc(Long kontoNr, Long bankId, Double balance, Order order, User user, Type typeEnum) {
        this.kontoNr = kontoNr;
        this.bankId = bankId;
        this.balance = balance;
        this.order = order;
        this.user = user;
        this.typeEnum = typeEnum;
        idCounter += 1;
        this.id = idCounter;
    }

    public static Long getIdCounter() {
        return idCounter;
    }

    public Long getId() {
        return id;
    }

    public Long getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(Long kontoNr) {
        this.kontoNr = kontoNr;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(Type typeEnum) {
        this.typeEnum = typeEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAcc bankAcc = (BankAcc) o;
        return Objects.equals(id, bankAcc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kontoNr, bankId, balance, order, user);
    }

    @Override
    public String toString() {
        return "BankAcc{" +
                "id=" + id +
                ", kontoNr=" + kontoNr +
                ", blz=" + bankId +
                ", balance=" + balance +
                ", order=" + order +
                ", typeEnum=" + typeEnum +
                '}';
    }
}
