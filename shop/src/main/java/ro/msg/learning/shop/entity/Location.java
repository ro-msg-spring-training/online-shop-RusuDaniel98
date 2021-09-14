package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;

@Entity
@Table(name="location")
@Data
@NoArgsConstructor
public class Location extends BaseEntity {

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="address_country")
    private String addressCountry;

    @Column(name="address_city")
    private String addressCity;

    @Column(name="address_county")
    private String addressCounty;

    @Column(name="address_street_address")
    private String addressStreetAddress;

    public Location(String name, String addressCountry, String addressCity, String addressCounty, String addressStreetAddress) {
        this.name = name;
        this.addressCountry = addressCountry;
        this.addressCity = addressCity;
        this.addressCounty = addressCounty;
        this.addressStreetAddress = addressStreetAddress;
    }
}
