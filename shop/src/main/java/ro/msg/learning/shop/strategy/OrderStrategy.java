package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.Stock;

import java.util.List;

public interface OrderStrategy {

    List<Stock> findShippingLocation(List<OrderDetail> orderDetails, List<Stock> stocks) throws Exception;

}
