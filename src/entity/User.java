package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class User {
    private static Long idCounter = 0L;
    private final Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Integer cityCode;
    private final List<BankAcc> bankAccList = new ArrayList<>();
    private Long phoneNr;
    private Order order;

    public User(String firstName, String lastName, String address, Integer cityCode, Long phoneNr, Order order) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cityCode = cityCode;
        this.phoneNr = phoneNr;
        this.order = order;
        idCounter += 1;
        this.id = idCounter;
    }

    public void addBankAcc(BankAcc bankAcc) {
        this.bankAccList.add(bankAcc);
    }

    public List<BankAcc> getBankAccList() {
        return bankAccList;
    }

    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings("unused")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @SuppressWarnings("unused")
    public String getLastName() {
        return lastName;
    }

    @SuppressWarnings("unused")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @SuppressWarnings("unused")
    public String getAddress() {
        return address;
    }

    @SuppressWarnings("unused")
    public void setAddress(String address) {
        this.address = address;
    }

    @SuppressWarnings("unused")
    public Integer getCityCode() {
        return cityCode;
    }

    @SuppressWarnings("unused")
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    @SuppressWarnings("unused")
    public Long getPhoneNr() {
        return phoneNr;
    }

    @SuppressWarnings("unused")
    public void setPhoneNr(Long phoneNr) {
        this.phoneNr = phoneNr;
    }

    @SuppressWarnings("unused")
    public Order getOrder() {
        return order;
    }

    @SuppressWarnings("unused")
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, cityCode, phoneNr, order, bankAccList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", cityCode=" + cityCode +
                ", phoneNr=" + phoneNr +
                ", orderId=" + order +
                '}';
    }
}
