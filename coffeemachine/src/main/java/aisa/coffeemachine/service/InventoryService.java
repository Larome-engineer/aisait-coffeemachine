package aisa.coffeemachine.service;

import aisa.coffeemachine.domain.Inventory;
import aisa.coffeemachine.dto.inventory.InventoryRequest;
import aisa.coffeemachine.dto.inventory.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> getAllInventories();
    InventoryResponse getInventoryByName(String name);
    Inventory requestToInventory(InventoryRequest inventoryRequest);

    void createInventory(InventoryRequest inventoryRequest);
    void topUpInventory(String name, int number);
    void reduceInventory(String name, int number);
}
