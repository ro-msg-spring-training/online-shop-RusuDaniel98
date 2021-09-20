package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dao.*;
import ro.msg.learning.shop.entity.*;

import java.math.BigDecimal;

@Service
@Profile("test")
public class DummyDatabaseRecordsService {

    @Autowired
    private IProductDAO productDAO;
    @Autowired
    private IProductCategoryDAO productCategoryDAO;
    @Autowired
    private ISupplierDAO supplierDAO;
    @Autowired
    private ICustomerDAO customerDAO;
    @Autowired
    private IRevenueDAO revenueDAO;
    @Autowired
    private IStockDAO stockDAO;
    @Autowired
    private IOrderDetailDAO orderDetailDAO;
    @Autowired
    private ILocationDAO locationDAO;
    @Autowired
    private IOrderDAO orderDAO;

    // adds dummy records in the database
    public void addProducts() {
        ProductCategory productCategory = new ProductCategory(
                "peripheral",
                "peripheral devices for PC"
        );
        productCategoryDAO.save(productCategory);

        Supplier supplier = new Supplier("IBM");
        supplierDAO.save(supplier);

        Product product = new Product(
                "keyboardOne",
                "first keyboard model",
                BigDecimal.valueOf(50),
                2.2,
                productCategory,
                supplier,
                "my.url.keyboard-one.com"
        );
        productDAO.save(product);

        Location location = new Location(
                "Main Storage Cluj-Napoca",
                "Romania",
                "Cluj-Napoca",
                "Cluj",
                "Bucuresti 25"
        );
        locationDAO.save(location);

        Stock stock = new Stock(
                15,
                product,
                location
        );
        stockDAO.save(stock);

        productCategory = new ProductCategory(
                "audio",
                "headsets, speakers and earbuds"
        );
        productCategoryDAO.save(productCategory);

        supplier = new Supplier("Sennheiser");
        supplierDAO.save(supplier);

        product = new Product(
                "HeadsetOne",
                "Our first headset model",
                BigDecimal.valueOf(100),
                0.3,
                productCategory,
                supplier,
                "my.url.headset-one.com"
        );
        productDAO.save(product);

        stock = new Stock(
                7,
                product,
                location
        );
        stockDAO.save(stock);

        location = new Location(
                "Physical Shop Alba Iulia",
                "Romania",
                "Alba Iulia",
                "Alba",
                "Traian 50"
        );
        locationDAO.save(location);

        stock = new Stock(
                4,
                product,
                location
        );
        stockDAO.save(stock);
    }

    // adds dummy values for table "customer"
    public void addCustomers() {
        Customer customer = new Customer(
                "Daniel",
                "Rusu",
                "danielrusu",
                "rusudaniel",
                "danielrusu@gmail.com"
        );
        customerDAO.save(customer);

        customer = new Customer(
                "John",
                "Doe",
                "johndoe",
                "doejohn",
                "johndoe@gmail.com"
        );
        customerDAO.save(customer);
    }

    // deletes all the records in the database.
    public void deleteAllRecords() {
        stockDAO.deleteAll();
        productDAO.deleteAll();
        productCategoryDAO.deleteAll();
        supplierDAO.deleteAll();
        customerDAO.deleteAll();
        revenueDAO.deleteAll();
        orderDetailDAO.deleteAll();
        locationDAO.deleteAll();
        orderDAO.deleteAll();
    }


}
