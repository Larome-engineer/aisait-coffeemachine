package aisa.coffeemachine.repository;

import java.util.List;

import aisa.coffeemachine.domain.RecipeInventory;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RecipeInventoryRepository extends JpaRepository<RecipeInventory, Integer> {
    @Query(nativeQuery = true, value = "select * from recipe_inventory where ri_recipe_id=:recipeId")
    List<RecipeInventory> findAllByRecipeId(int recipeId);
}
