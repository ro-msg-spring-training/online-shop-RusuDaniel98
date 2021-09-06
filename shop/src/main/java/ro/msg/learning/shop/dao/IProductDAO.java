package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Product;

public interface IProductDAO extends JpaRepository<Product, Integer> {

    Product findById(int id);

}
