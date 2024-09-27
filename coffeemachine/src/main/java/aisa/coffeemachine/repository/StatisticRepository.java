package aisa.coffeemachine.repository;

import java.util.Map;
import java.util.Date;

import aisa.coffeemachine.domain.Statistic;

import lombok.Value;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "delete from statistics s where s.stat_start_date <= :deletedDate" )
    void deleteByDate(@Param("deletedDate") Date deletedDate);

    // most by number of quantity (самый заказываемый по кол-ву)
    @Query(nativeQuery = true, value = "select stat_drink_id, cast(sum(stat_quantity) as int) as total_quantity " +
            "from statistics " +
            "group by stat_drink_id " +
            "order by total_quantity desc limit 1;")
    Map<Integer, Integer> getMostPopularDrinkByQuantity();

    // most by number of order (самый заказываемый по позиции)
    @Query(nativeQuery = true,
            value = "select stat_drink_id, cast(count(*) as int) as total_count " +
                    "from statistics " +
                    "group by stat_drink_id " +
                    "order by total_count desc limit 1;")
    Map<Integer, Integer> getMostPopularDrinkByOrder();

}
