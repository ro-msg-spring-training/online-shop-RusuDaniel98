package ro.msg.learning.shop.strategy;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.Stock;

import java.util.ArrayList;
import java.util.List;

@Component
@Log
public class OrderStrategyMostAbundant implements OrderStrategy {


    @Override
    public List<Stock> findShippingLocation(List<OrderDetail> orderDetails, List<Stock> stocks) {

        List<Stock> orderStocks = new ArrayList<>();

        log.severe("Most abundant shipping locations requested");

        return orderStocks;
    }
}
