package aisa.coffeemachine.dto.inventory;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Inventory entity")
public record InventoryResponse(
        @Schema(description = "Inventory name")
        String inventoryName,
        @Schema(description = "Quantity of inventory")
        int inventoryNum,
        @Schema(description = "Unit of inventory")
        String inventoryUnit) {
}
