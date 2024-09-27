package aisa.coffeemachine.dto.recipe;

import aisa.coffeemachine.dto.inventory.InventoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Recipe entity")
public record RecipeResponse(
        @Schema(description = "Recipe description")
        String recipeDesc,

        @Schema(description = "List of inventories entities")
        List<InventoryResponse> inventories) {
}

