package aisa.coffeemachine.dto.order;

import aisa.coffeemachine.dto.drinks.DrinkResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "Order entity")
public record OrderResponse(
        @Schema(description = "Date when order was done")
        Date date,

        @Schema(description = "Quantity of drink")
        int quantity,

        @Schema(description = "Drink entity")
        DrinkResponse drinkResponse
) {
}
