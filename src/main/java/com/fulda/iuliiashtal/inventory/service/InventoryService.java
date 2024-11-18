package com.fulda.iuliiashtal.inventory.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InventoryService {

    private final Map<UUID, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put(UUID.fromString("d226ced6-cc7d-4bc0-a18a-b18e9f2e799b"), 3);
        inventory.put(UUID.fromString("61cc7226-440b-41fc-b29f-afbdac60851e"), 10);
        inventory.put(UUID.fromString("bc8d91de-429e-4bd6-923c-a425640744be"), 1);
    }

    public void addStock(UUID productId, int count) {
        inventory.put(productId, inventory.getOrDefault(productId, 0) + count);
    }

    public int getStockForProductId(UUID productId) {
        return inventory.getOrDefault(productId, 0);
    }

    public boolean reduceStockForProductId(UUID productId) {
        int currentStock = getStockForProductId(productId);
        if (currentStock > 0) {
            inventory.put(productId, currentStock - 1);
            return true;
        }
        return false;
    }
}
