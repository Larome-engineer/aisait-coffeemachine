package aisa.coffeemachine.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Order entity")
public record OrderRequest(
        @Schema(description = "Drink name")
        String drinkName,

        @Schema(description = "Quantity of drinks")
        int quantity
) {
}
