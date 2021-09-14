package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="weight")
    private Double weight;

    @ManyToOne
    @JoinColumn(name="category")
    private ProductCategory productCategory;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="supplier")
    private Supplier supplier;

    @Column(name="image_url")
    private String imageUrl;

    public Product(String name, String description, BigDecimal price, Double weight, ProductCategory productCategory,
                   Supplier supplier, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.imageUrl = imageUrl;
    }
}
