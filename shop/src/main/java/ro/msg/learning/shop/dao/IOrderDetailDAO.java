package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.OrderDetail;

public interface IOrderDetailDAO extends JpaRepository<OrderDetail, Integer> {

    OrderDetail findById(int id);

}
