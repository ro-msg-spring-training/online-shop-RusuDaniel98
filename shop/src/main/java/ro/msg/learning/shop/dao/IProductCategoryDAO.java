package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.ProductCategory;

public interface IProductCategoryDAO extends JpaRepository<ProductCategory, Integer> {

    ProductCategory findById(int id);

}
