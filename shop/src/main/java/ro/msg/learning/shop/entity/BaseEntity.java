package ro.msg.learning.shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Data @NoArgsConstructor
public class BaseEntity {

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
}
