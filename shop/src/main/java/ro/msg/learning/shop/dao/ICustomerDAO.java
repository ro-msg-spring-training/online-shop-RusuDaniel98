package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Customer;

public interface ICustomerDAO extends JpaRepository<Customer, Integer> {

    Customer findById(int id);

}
