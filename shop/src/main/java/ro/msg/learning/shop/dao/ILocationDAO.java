package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Location;

public interface ILocationDAO extends JpaRepository<Location, Integer> {

    Location findById(int id);

}
