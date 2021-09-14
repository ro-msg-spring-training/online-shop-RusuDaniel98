package ro.msg.learning.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.OrderDetail;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderPlacementDTO {

    private LocalDateTime timestamp;

    private String addressCountry;

    private String addressCity;

    private String addressCounty;

    private String addressStreetAddress;

    private List<OrderDetail> orderDetails;

}
