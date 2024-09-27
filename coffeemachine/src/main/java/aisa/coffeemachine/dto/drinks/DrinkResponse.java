package aisa.coffeemachine.dto.drinks;

import aisa.coffeemachine.dto.recipe.RecipeResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Drink entity")
public record DrinkResponse (

        @Schema(description = "Drink name")
        String drinkName,

        @Schema(description = "Recipe entity")
        RecipeResponse recipe
) { }