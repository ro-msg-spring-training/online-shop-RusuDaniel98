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

    public OrderPlacementDTO(LocalDateTime timestamp,
                             String addressCountry,
                             String addressCity,
                             String addressCounty,
                             String addressStreetAddress,
                             List<OrderDetail> orderDetails) {
        this.timestamp = timestamp;
        this.addressCountry = addressCountry;
        this.addressCity = addressCity;
        this.addressCounty = addressCounty;
        this.addressStreetAddress = addressStreetAddress;
        this.orderDetails = orderDetails;
    }
}
