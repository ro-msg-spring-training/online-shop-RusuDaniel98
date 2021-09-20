package ro.msg.learning.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.msg.learning.shop.service.OrderCreationService;

public class OrderCreationTests {

    private OrderCreationService orderCreationService;

    @BeforeEach
    void initOrderCreationService() {
        orderCreationService = new OrderCreationService();
    }

    @Test
    void singleLocationStrategyIsComputed() {
        orderCreationService.setStrategyName("single_location");
        assert(orderCreationService.findStrategy().getClass().getName()
                .equals("ro.msg.learning.shop.strategy.OrderStrategySingleLocation"));
    }

    @Test
    void mostAbundantStrategyIsComputed() {
        orderCreationService.setStrategyName("most_abundant");
        assert(orderCreationService.findStrategy().getClass().getName()
                .equals("ro.msg.learning.shop.strategy.OrderStrategyMostAbundant"));
    }

}
