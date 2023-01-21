package entity;

import java.util.Objects;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class BankAcc {

    private static Long idCounter = 0L;

    private final Long id;
    private Long kontoNr;
    private Long blz;
    private Double balance;
    private Long orderId;
    private Long userId;
    private Type typeEnum;

    public BankAcc(Long kontoNr, Long blz, Double balance, Long orderId, Type typeEnum) {
        this.kontoNr = kontoNr;
        this.blz = blz;
        this.balance = balance;
        this.orderId = orderId;
        this.typeEnum = typeEnum;
        idCounter += 1;
        this.id = idCounter;
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

    public Long getBlz() {
        return blz;
    }

    public void setBlz(Long blz) {
        this.blz = blz;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return Objects.hash(kontoNr, blz, balance, orderId, userId);
    }

    @Override
    public String toString() {
        return "BankAcc{" +
                "id=" + id +
                ", kontoNr=" + kontoNr +
                ", blz=" + blz +
                ", balance=" + balance +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", typeEnum=" + typeEnum +
                '}';
    }
}
