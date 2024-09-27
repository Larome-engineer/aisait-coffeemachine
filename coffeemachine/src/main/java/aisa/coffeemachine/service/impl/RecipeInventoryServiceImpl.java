package aisa.coffeemachine.service.impl;

import java.util.List;

import aisa.coffeemachine.domain.Drink;
import aisa.coffeemachine.domain.RecipeInventory;
import aisa.coffeemachine.service.RecipeInventoryService;
import aisa.coffeemachine.repository.RecipeInventoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeInventoryServiceImpl implements RecipeInventoryService {

    private final RecipeInventoryRepository recipeInventoryRepository;

    @Override
    public List<RecipeInventory> findAllByRecipeId(Drink drink) { // inner method to use other services
        return recipeInventoryRepository.findAllByRecipeId(drink.getRecipe().getRecipeId());
    }

    @Override
    public void saveAll(List<RecipeInventory> recipeInventories) { // inner method to use other services
        recipeInventoryRepository.saveAll(recipeInventories);
    }
}
