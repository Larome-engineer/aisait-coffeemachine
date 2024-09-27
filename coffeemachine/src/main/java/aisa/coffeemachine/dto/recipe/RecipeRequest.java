package aisa.coffeemachine.dto.recipe;

import aisa.coffeemachine.dto.inventory.InventoryRequest;

import java.util.List;

public record RecipeRequest(String recipeDesc, List<InventoryRequest> inventories
) {
}
