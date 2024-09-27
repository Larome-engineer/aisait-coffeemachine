package aisa.coffeemachine.dto.statistic;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistic entity")
public record StatisticResponse(
        @Schema(description = "Drink name")
        String drinkName,
        @Schema(description = "Quantity of drinks | orders")
        int numOf
) {
}
