package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Revenue;

public interface IRevenueDAO extends JpaRepository<Revenue, Integer> {

    Revenue findById(int id);

}
