package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Supplier;

public interface ISupplierDAO extends JpaRepository<Supplier, Integer> {

    Supplier findById(int id);

}
