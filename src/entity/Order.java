package entity;

import java.util.Objects;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class Order {

    private static Long idCounter = 0L;

    private Long id;

    public Order() {
        idCounter += 1;
        this.id = idCounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}
