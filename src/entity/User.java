package entity;

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
    private Long phoneNr;
    private Long orderId;
    private Long bankAccId;

    public User(String firstName, String lastName, String address, Integer cityCode, Long phoneNr, Long orderId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cityCode = cityCode;
        this.phoneNr = phoneNr;
        this.orderId = orderId;
        idCounter += 1;
        this.id = idCounter;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Long getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(Long phoneNr) {
        this.phoneNr = phoneNr;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBankAccId() {
        return bankAccId;
    }

    public void setBankAccId(Long bankAccId) {
        this.bankAccId = bankAccId;
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
        return Objects.hash(id, firstName, lastName, address, cityCode, phoneNr, orderId, bankAccId);
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
                ", orderId=" + orderId +
                ", bankAccId=" + bankAccId +
                '}';
    }
}
