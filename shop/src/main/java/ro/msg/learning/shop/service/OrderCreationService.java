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

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log
public class OrderCreationService {

    private final String strategyName = "single_location";

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

    public Order createOrder(
            LocalDateTime timestamp,
            String addressCountry,
            String addressCity,
            String addressCounty,
            String addressStreetAddress,
            List<OrderDetail> orderDetails) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(OrderCreationConfiguration.class);

        Order order = new Order();
        OrderStrategy strategy;
        List<Stock> stocks = stockDAO.findAll();

        if (strategyName.equals("single_location")) {
            strategy = (OrderStrategy) context.getBean("strategySingleLocation");
            List<Stock> orderStocks = null;
            try {
                orderStocks = strategy.findShippingLocation(orderDetails, stocks);
                // decrease the quantity value for each stock

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
            strategy = (OrderStrategy) context.getBean("strategyMostAbundant");

            // NOTE: if a Order could have multiple Shipping Locations, the database relationships may need to change??
        }

        return order;
    }

}
