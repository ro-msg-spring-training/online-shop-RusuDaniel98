package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="customer_order")
@Data
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="address_country")
    private String addressCountry;

    @Column(name="address_city")
    private String addressCity;

    @Column(name="address_county")
    private String addressCounty;

    @Column(name="address_street_address")
    private String addressStreetAddress;

    @ManyToOne
    @JoinColumn(name="shipped_from")
    private Location shippedFrom;

    @ManyToOne
    @JoinColumn(name="customer")
    private Customer customer;

    public Order(LocalDateTime createdAt,
                 String addressCountry,
                 String addressCity,
                 String addressCounty,
                 String addressStreetAddress,
                 Location shippedFrom,
                 Customer customer) {

        this.createdAt = createdAt;
        this.addressCountry = addressCountry;
        this.addressCity = addressCity;
        this.addressCounty = addressCounty;
        this.addressStreetAddress = addressStreetAddress;
        this.shippedFrom = shippedFrom;
        this.customer = customer;
    }


}
