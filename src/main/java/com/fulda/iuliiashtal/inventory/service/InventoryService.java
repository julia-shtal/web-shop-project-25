package com.fulda.iuliiashtal.inventory.service;

import com.fulda.iuliiashtal.inventory.repository.InventoryRepositoryLocal;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepositoryLocal repository;

    private Map<UUID, Integer> inventory;

    public InventoryService(InventoryRepositoryLocal repository) {
        this.repository = repository;
        inventory = repository.readFromJson();
        if (inventory == null) {
            inventory = new HashMap<>(); // Initialize to avoid null pointer exceptions if loading fails.
        }
    }

    private Map<UUID, Integer> getInventory() {
        inventory = repository.readFromJson();
        return inventory;
    }

    public void addStock(UUID productId, int count) {
        getInventory().put(productId, inventory.getOrDefault(productId, 0) + count);
        repository.writeToJson(inventory);
    }

    public int getStockForProductId(UUID productId) {
        return getInventory().getOrDefault(productId, 0);
    }

    public boolean reduceStockForProductId(UUID productId) {
        int currentStock = getStockForProductId(productId);
        if (currentStock > 0) {
            getInventory().put(productId, currentStock - 1);
            repository.writeToJson(inventory);
            return true;
        }
        return false;
    }
}
