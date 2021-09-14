package ro.msg.learning.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Supplier;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductAndCategoryDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private Double weight;

    private String imageUrl;

    private Supplier supplier;

    private String categoryName;

    private String categoryDescription;

}
