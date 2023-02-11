package entity;

import java.util.Objects;

/**
 * @author Andrej Reutow
 * created on 21.01.2023
 */
public class Order {

    private static Long idCounter = 0L;

    private Long id;

    private OrderStatus orderStatus;

    public Order() {
        idCounter += 1;
        this.id = idCounter;
        this.orderStatus = OrderStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Long idCounter) {
        Order.idCounter = idCounter;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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
