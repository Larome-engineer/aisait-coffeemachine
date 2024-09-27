package aisa.coffeemachine.dto.drinks;

import aisa.coffeemachine.dto.recipe.RecipeRequest;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Drink entity")
public record DrinkRequest(
        @Schema(description = "Drink name")
        String drinkName,

        @Schema(description = "Recipe entity")
        RecipeRequest recipe
) {}
