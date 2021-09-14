package ro.msg.learning.shop.strategy;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@Log
public class OrderStrategySingleLocation implements OrderStrategy {

    @Override
    public List<Stock> findShippingLocation(List<OrderDetail> orderDetails, List<Stock> stocks) throws Exception {
        List<Stock> orderStocks = new ArrayList<>();
        // using a set will not allow duplicates.
        HashSet<Location> locationSet = new HashSet<>();
        for (Stock stock : stocks) {
            locationSet.add(stock.getLocation());
        }
        // convert the set to list.
        List<Location> locationList = new ArrayList<>(locationSet);
        // find the first location that complies to the strategy.
        for (Location location : locationList) {
            for (Stock stock : stocks) {
                // only take into consideration stocks from the current location.
                if (stock.getLocation().equals(location)) {
                    for (OrderDetail orderDetail : orderDetails) {
                        // all order details (products and quantities) must be found from the same location.
                        if (stock.getProduct().getId() == orderDetail.getProduct().getId() &&
                            stock.getQuantity() >= orderDetail.getQuantity()) {
                            // found one product that can be ordered from here.
                            Stock orderStock = new Stock(orderDetail.getQuantity(), stock.getProduct(), location);
                            orderStocks.add(orderStock);
                        }
                    }
                }
            }
            if (orderStocks.size() == orderDetails.size()) {
                // all the items can be ordered from the same location.
                // decrease the stocks' quantities and stop the search.
                for (Stock orderStock : orderStocks) {
                    for (Stock stock : stocks) {
                        if (stock.getProduct().equals(orderStock.getProduct()) &&
                                stock.getLocation().equals(location)) {
                            stock.setQuantity(stock.getQuantity() - orderStock.getQuantity());
                        }
                    }
                }
                return orderStocks;
            }
            orderStocks.clear();
        }
        // if this point is reached, the order cound not be completed from a single location.
        throw new Exception("Single location could not be found for current order.");
    }

}
