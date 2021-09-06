package ro.msg.learning.shop.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="customer")
@Data @NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @NonNull
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email_address")
    private String emailAddress;

}
