package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Order;

public interface IOrderDAO extends JpaRepository<Order, Integer> {

    Order findById(int id);

}
