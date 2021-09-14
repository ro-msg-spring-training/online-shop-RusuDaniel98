package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="order_detail")
@Data
@NoArgsConstructor
public class OrderDetail extends BaseEntity {

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="customer_order")
    private Order order;

    public OrderDetail(int quantity, Product product, Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }
}
