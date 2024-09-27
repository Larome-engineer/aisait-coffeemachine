package aisa.coffeemachine.repository;

import java.util.Date;
import java.util.List;

import aisa.coffeemachine.domain.Order;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(nativeQuery = true, value = "select * from orders o " +
            "left join drinks d on d.drink_id=o.o_drink_id " +
            "where drink_name=:name;")
    List<Order> findByDrinkName(String name);

    @Query(nativeQuery = true, value = "select * from orders o " +
            "left join drinks d on d.drink_id=o.o_drink_id " +
            "where o_date=:date;")
    List<Order> findByDate(Date date);

}
