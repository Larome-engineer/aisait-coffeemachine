package aisa.coffeemachine.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import aisa.coffeemachine.domain.Drink;
import aisa.coffeemachine.domain.Recipe;
import aisa.coffeemachine.domain.Inventory;

import aisa.coffeemachine.mappers.Mapper;
import aisa.coffeemachine.service.DrinkService;
import aisa.coffeemachine.domain.RecipeInventory;
import aisa.coffeemachine.dto.drinks.DrinkRequest;
import aisa.coffeemachine.service.InventoryService;
import aisa.coffeemachine.dto.drinks.DrinkResponse;
import aisa.coffeemachine.repository.DrinkRepository;
import aisa.coffeemachine.dto.inventory.InventoryRequest;
import aisa.coffeemachine.service.RecipeInventoryService;
import aisa.coffeemachine.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final Mapper mapper;
    private final DrinkRepository drinkRepository;
    private final InventoryService inventoryService;
    private final RecipeInventoryService recipeInventoryService;

    @Override
    public List<DrinkResponse> getAllDrinks() {
        List<Drink> drinks = drinkRepository.findAll();
        if (drinks.isEmpty()) {
            throw new ResourceNotFoundException("No drinks found.");
        }

        return drinks.stream()
                .filter(this::hasIngredients)
                .map(this::enrichDrinkWithInventory)
                .map(mapper::drinkToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DrinkResponse getDrinkResponseByName(String name) {
        return drinkRepository.getByName(name)
                .map(this::enrichDrinkWithInventory)
                .map(drink -> new DrinkResponse(
                        drink.getDrinkName(),
                        mapper.recipeToResponse(drink.getRecipe())
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Drink with name: " + name + " not found"));
    }

    @Override
    @Transactional
    public void addNewDrink(DrinkRequest drinkRequest) {
        try {
            Drink drink = Drink.builder().drinkName(drinkRequest.drinkName()).build();
            Recipe recipe = Recipe.builder()
                    .recipeDesc(drinkRequest.recipe().recipeDesc())
                    .drink(drink)
                    .build();

            List<RecipeInventory> recipeInventoriesToCreate = createRecipeInventories(drinkRequest.recipe().inventories(), recipe);
            recipeInventoryService.saveAll(recipeInventoriesToCreate);
            drinkRepository.save(drink);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid inventory request: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Error creating drink in the database.");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error creating drink or drink already exists.");
        }
    }

    @Override
    public Drink getById(int drinkId) { // inner method for other service
        return drinkRepository.findById(drinkId).orElse(null);
    }

    @Override
    public Drink getDrinkByName(String name) { // inner method for other service
        return drinkRepository.getByName(name).orElse(null);
    }

    private boolean hasIngredients(Drink drink) {
        return !drink.getRecipe().getInventories().isEmpty();
    }


    private Inventory createInventoryFromRecipeInventory(RecipeInventory recipeInventory) {
        return new Inventory(
                recipeInventory.getInventory().getId(),
                recipeInventory.getInventory().getInventoryName(),
                recipeInventory.getNumOfIngredient(),
                recipeInventory.getInventory().getInventoryUnit(),
                recipeInventory.getInventory().getRecipes()
        );
    }

    private Drink enrichDrinkWithInventory(Drink drink) {
        List<Inventory> inventories = recipeInventoryService
                .findAllByRecipeId(drink)
                .stream()
                .map(this::createInventoryFromRecipeInventory)
                .collect(Collectors.toList());
        drink.getRecipe().setInventories(inventories);
        return drink;
    }

    private List<RecipeInventory> createRecipeInventories(List<InventoryRequest> inventoryRequests, Recipe recipe) {
        return inventoryRequests.stream()
                .map(inventoryRequest -> RecipeInventory.builder()
                        .recipe(recipe)
                        .inventory(inventoryService.requestToInventory(inventoryRequest))
                        .numOfIngredient(inventoryRequest.inventoryNum())
                        .build())
                .collect(Collectors.toList());
    }


}
