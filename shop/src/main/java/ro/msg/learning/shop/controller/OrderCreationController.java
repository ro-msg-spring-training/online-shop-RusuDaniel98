package ro.msg.learning.shop.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderPlacementDTO;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.service.OrderCreationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log
public class OrderCreationController {

    @Autowired
    OrderCreationService orderCreationService;

    @PostMapping("/order")
    private Order createOrder(@RequestBody OrderPlacementDTO orderPlacementDTO) {

        Order order;
        try {
            order = orderCreationService.createOrder(
                orderPlacementDTO.getTimestamp(),
                    orderPlacementDTO.getAddressCountry(),
                    orderPlacementDTO.getAddressCity(),
                    orderPlacementDTO.getAddressCounty(),
                    orderPlacementDTO.getAddressStreetAddress(),
                    orderPlacementDTO.getOrderDetails()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return order;
    }

}
