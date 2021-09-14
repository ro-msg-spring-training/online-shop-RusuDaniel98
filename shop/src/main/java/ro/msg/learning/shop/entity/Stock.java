package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="stock")
@Data
@NoArgsConstructor
public class Stock extends BaseEntity {

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
    @JoinColumn(name="location")
    private Location location;

    public Stock(int quantity, Product product, Location location) {
        this.quantity = quantity;
        this.product = product;
        this.location = location;
    }
}
