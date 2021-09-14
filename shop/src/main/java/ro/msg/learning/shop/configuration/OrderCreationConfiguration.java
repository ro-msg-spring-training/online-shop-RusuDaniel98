package ro.msg.learning.shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.OrderStrategy;
import ro.msg.learning.shop.strategy.OrderStrategyMostAbundant;
import ro.msg.learning.shop.strategy.OrderStrategySingleLocation;

@Configuration
public class OrderCreationConfiguration {

    @Bean(name="strategySingleLocation")
    public OrderStrategy strategySingleLocation() {
        return new OrderStrategySingleLocation();
    }

    @Bean(name="strategyMostAbundant")
    public OrderStrategy strategyMostAbundant() {
        return new OrderStrategyMostAbundant();
    }

}
