package aisa.coffeemachine.service.impl;

import java.util.List;

import aisa.coffeemachine.mappers.Mapper;
import aisa.coffeemachine.domain.Inventory;
import aisa.coffeemachine.service.InventoryService;
import aisa.coffeemachine.exception.SomethingWentWrong;
import aisa.coffeemachine.repository.InventoryRepository;
import aisa.coffeemachine.dto.inventory.InventoryRequest;
import aisa.coffeemachine.dto.inventory.InventoryResponse;
import aisa.coffeemachine.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final Mapper mapper;
    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryResponse> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        if (inventories.isEmpty()) {
            throw new ResourceNotFoundException("No inventories");
        }
        return inventories
                .stream()
                .map(mapper::inventToResponse)
                .toList();
    }

    @Override
    public InventoryResponse getInventoryByName(String name) {
        return inventoryRepository.findByName(name)
                .map(mapper::inventToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with name: " + name + " not found"));
    }

    @Override
    @Transactional
    public void createInventory(InventoryRequest inventoryRequest) {
        try {
            inventoryRepository.save(mapper.mapToInventory(inventoryRequest));
        } catch (SomethingWentWrong wentWrong) {
            throw new ResourceNotFoundException("Error to creating inventory");
        }
    }

    @Override
    @Transactional
    @Modifying
    public void topUpInventory(String name, int number) {
        Inventory inventory = inventoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with name: " + name + " not found"));

        inventory.setInventoryNum(inventory.getInventoryNum() + number);
        inventoryRepository.save(inventory);
    }


    @Override
    @Transactional
    @Modifying
    public void reduceInventory(String name, int number) {
        Inventory inventory = inventoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory with name: " + name + " not found"));

        int quantityOfInventory = inventory.getInventoryNum();
        if (quantityOfInventory - number < 0) {
            throw new ResourceNotFoundException("Unable to create order: Insufficient inventory: "
                    + inventory.getInventoryName());
        }

        inventory.setInventoryNum(quantityOfInventory - number);
        inventoryRepository.save(inventory);
    }

    public Inventory requestToInventory(InventoryRequest inventoryRequest) {
        return inventoryRepository.findByName(inventoryRequest.inventoryName())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory doesn't exists"));
    }

}
