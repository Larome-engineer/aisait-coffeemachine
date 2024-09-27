package aisa.coffeemachine.repository;

import java.util.Optional;

import aisa.coffeemachine.domain.Drink;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    @Query(nativeQuery = true, value = "select * from drinks d where d.drink_name=:name;")
    Optional<Drink> getByName(String name);
}
