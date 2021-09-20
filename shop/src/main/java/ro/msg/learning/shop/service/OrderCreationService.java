package ro.msg.learning.shop.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configuration.OrderCreationConfiguration;
import ro.msg.learning.shop.dao.*;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.strategy.OrderStrategy;
import ro.msg.learning.shop.strategy.OrderStrategySingleLocation;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log
public class OrderCreationService {

    private String strategyName = "single_location";

    @Autowired
    private IStockDAO stockDAO;
    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IOrderDetailDAO orderDetailDAO;
    @Autowired
    private IProductDAO productDAO;
    @Autowired
    private ICustomerDAO customerDAO;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public OrderStrategy findStrategy() {
        ApplicationContext context = new AnnotationConfigApplicationContext(OrderCreationConfiguration.class);
        if (strategyName.equals("single_location")) {
            return (OrderStrategy) context.getBean("strategySingleLocation");
        } else {
            return (OrderStrategy) context.getBean("strategyMostAbundant");
        }
    }

    public Order createOrder(
            LocalDateTime timestamp,
            String addressCountry,
            String addressCity,
            String addressCounty,
            String addressStreetAddress,
            List<OrderDetail> orderDetails) throws Exception {

        Order order = new Order();
        OrderStrategy strategy = findStrategy();

        List<Stock> stocks = stockDAO.findAll();

        if (strategy.getClass().getName().equals("ro.msg.learning.shop.strategy.OrderStrategySingleLocation")) {
            List<Stock> orderStocks = null;
            try {
                orderStocks = strategy.findShippingLocation(orderDetails, stocks);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Location shippingLocation;
            if (orderStocks == null) {
                throw new Exception("The order cannot be created.");
            } else {
                shippingLocation = orderStocks.get(0).getLocation();
                // dummy Customer object
                Customer customer = new Customer();
                customerDAO.save(customer);
                order = new Order(
                        timestamp,
                        addressCountry,
                        addressCity,
                        addressCounty,
                        addressStreetAddress,
                        shippingLocation,
                        customer
                );
            }
            orderDAO.save(order);

            // only if the right location was found (= no exception thrown), go through all the order details and
            // instantiate new OrderDetail object to generate IDs, then assign their attributes and save to database.
            for (OrderDetail orderDetail : orderDetails) {
                int productId = orderDetail.getProduct().getId();
                Product product = productDAO.findById(productId);
                OrderDetail detailToStore = new OrderDetail(orderDetail.getQuantity(), product, order);
                orderDetailDAO.save(detailToStore);
            }

        } else {
            // NOTE: if an Order could have multiple Shipping Locations, the database relationships may need to change??

            // TODO implement the search for most abundant locations: possible solution written below
            // Idea: alter the database and the entity Order class to hold a List<Location>
            // and only store one location if the strategy is "single location".
        }

        return order;
    }

}
