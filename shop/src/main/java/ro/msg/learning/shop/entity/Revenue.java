package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="revenue")
@Data
@NoArgsConstructor
public class Revenue extends BaseEntity {

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="revenue_date")
    private LocalDate revenueDate;

    @Column(name="revenue_sum")
    private BigDecimal revenueSum;

    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

}
