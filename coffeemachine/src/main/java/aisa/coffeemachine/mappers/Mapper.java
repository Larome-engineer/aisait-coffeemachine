package aisa.coffeemachine.mappers;

import java.util.Date;

import org.springframework.stereotype.Component;

import aisa.coffeemachine.domain.Order;
import aisa.coffeemachine.domain.Drink;
import aisa.coffeemachine.domain.Recipe;
import aisa.coffeemachine.domain.Inventory;
import aisa.coffeemachine.dto.order.OrderRequest;
import aisa.coffeemachine.dto.order.OrderResponse;
import aisa.coffeemachine.dto.drinks.DrinkResponse;
import aisa.coffeemachine.dto.recipe.RecipeResponse;
import aisa.coffeemachine.dto.inventory.InventoryRequest;
import aisa.coffeemachine.dto.inventory.InventoryResponse;

@Component
public class Mapper {

    public OrderResponse orderToResponse(Order order) {
        return new OrderResponse(
                order.getDate(),
                order.getQuantity(),
                drinkToResponse(order.getDrink())
        );
    }

    public Order requestToOrder(OrderRequest orderRequest, Drink drink, Date date) {
        return Order.builder()
                .date(date)
                .drink(drink)
                .quantity(orderRequest.quantity())
                .build();
    }

    public DrinkResponse drinkToResponse(Drink drink) {
        return new DrinkResponse(
                drink.getDrinkName(),
                recipeToResponse(drink.getRecipe())
        );
    }

    public InventoryResponse inventToResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getInventoryName(),
                inventory.getInventoryNum(),
                inventory.getInventoryUnit());
    }

    public RecipeResponse recipeToResponse(Recipe recipe) {
        return new RecipeResponse(
                recipe.getRecipeDesc(),
                recipe.getInventories().stream().map(this::inventToResponse).toList()
        );
    }


    public Inventory mapToInventory(InventoryRequest inventoryRequest) {
        return Inventory.builder()
                .inventoryName(inventoryRequest.inventoryName())
                .inventoryNum(inventoryRequest.inventoryNum())
                .inventoryUnit(inventoryRequest.inventoryUnit())
                .build();
    }
}
