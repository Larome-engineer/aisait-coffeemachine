package aisa.coffeemachine.repository;

import java.util.Optional;

import aisa.coffeemachine.domain.Inventory;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query(nativeQuery = true, value = "select * from inventories i where i.inv_name=:name;")
    Optional<Inventory> findByName(String name);

}
