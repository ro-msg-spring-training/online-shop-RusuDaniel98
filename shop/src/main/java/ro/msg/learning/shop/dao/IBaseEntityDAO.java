package ro.msg.learning.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.BaseEntity;

public interface IBaseEntityDAO extends JpaRepository<BaseEntity, Integer> {

    BaseEntity findById(int id);

}
