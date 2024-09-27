package aisa.coffeemachine.service;

import aisa.coffeemachine.domain.Drink;
import aisa.coffeemachine.domain.RecipeInventory;

import java.util.List;

public interface RecipeInventoryService {
    List<RecipeInventory> findAllByRecipeId(Drink drink);
    void saveAll(List<RecipeInventory> recipeInventories);
}
