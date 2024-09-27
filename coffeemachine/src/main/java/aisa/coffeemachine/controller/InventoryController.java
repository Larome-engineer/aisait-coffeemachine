package aisa.coffeemachine.controller;


import aisa.coffeemachine.dto.inventory.InventoryRequest;
import aisa.coffeemachine.dto.inventory.InventoryResponse;
import aisa.coffeemachine.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffeemachine/inventory")
@Tag(name = "Inventory controller", description = "Controller for manage inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @Operation(
            summary = "Get all inventory",
            description = "That's method use when you wanna get all inventories"
    )
    public List<InventoryResponse> getAll() {
        return inventoryService.getAllInventories();
    }


    @GetMapping("/byName")
    @Operation(
            summary = "Get inventory by name",
            description = "You can get inventory by name using this method"
    )
    public InventoryResponse getByName(
            @Parameter(description = "Inventory name")
            @RequestParam("name") String name) {
        return inventoryService.getInventoryByName(name);
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create inventory",
            description = "This method using when you wanna add new inventory into inventories"
    )
    public void create(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.createInventory(inventoryRequest);
    }

    @PatchMapping("/topUp")
    @Operation(
            summary = "Top up inventory",
            description = "Using this method if you wanna TOP UP inventory quantity"
    )
    public void topUp(
            @RequestParam("name") @Parameter(description = "Inventory name") String name,
            @RequestParam("num") @Parameter(description = "Quantity to top up") int number
    ) {
        inventoryService.topUpInventory(name, number);
    }

    @PatchMapping("/reduce")
    @Operation(
            summary = "Reduce inventory",
            description = "Using this method if you wanna REDUCE inventory quantity"
    )
    public void reduce(
            @RequestParam("name") @Parameter(description = "Inventory name") String name,
            @RequestParam("num") @Parameter(description = "Quantity to reduce") int number
    ) {
        inventoryService.reduceInventory(name, number);
    }

}
