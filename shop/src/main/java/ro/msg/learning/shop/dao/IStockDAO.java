package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Stock;

public interface IStockDAO extends JpaRepository<Stock, Integer> {

    Stock findById(int id);

}
