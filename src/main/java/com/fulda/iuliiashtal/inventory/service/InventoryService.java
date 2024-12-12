package com.fulda.iuliiashtal.inventory.service;

import com.fulda.iuliiashtal.inventory.repository.InventoryRepositoryLocal;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Service class responsible for managing product inventory.
 * <p>
 * This class provides functionality to manage stock levels for products,
 * including adding stock, retrieving stock levels, and reducing stock for a given product.
 * It interacts with a local repository to persist and retrieve inventory data.
 * </p>
 * <p>
 * This class is annotated as a Spring {@link Service} and uses dependency injection
 * to interact with the {@link InventoryRepositoryLocal}.
 */
@Service
public class InventoryService {

    /**
     * Repository responsible for persisting and retrieving inventory data.
     */
    private final InventoryRepositoryLocal repository;

    /**
     * In-memory representation of the product inventory, where the key is the product's unique identifier
     * and the value is the stock count.
     */
    private Map<UUID, Integer> inventory;

    /**
     * Constructs an {@link InventoryService} instance.
     * <p>
     * Initializes the inventory map by reading data from the repository. If no data is available,
     * a new {@link HashMap} is created to represent the inventory.
     * </p>
     *
     * @param repository the repository used for persisting and retrieving inventory data.
     */
    public InventoryService(InventoryRepositoryLocal repository) {
        this.repository = repository;
    }

    public void clearInventory() {
        inventory = new HashMap<>();
        repository.writeEmptyToJson();
    }

    /**
     * Retrieves the current inventory from the repository.
     *
     * @return the current inventory map.
     */
    private Map<UUID, Integer> getInventory() {
        inventory = repository.readFromJson();
        return inventory;
    }

    /**
     * Adds stock for a specified product.
     * <p>
     * If the product already exists in the inventory, the specified count is added to the existing stock.
     * If the product does not exist, it is added to the inventory with the specified count.
     * </p>
     *
     * @param productId the unique identifier of the product.
     * @param count     the number of items to add to the stock.
     */
    public void addStock(UUID productId, int count) {
        getInventory().put(productId, inventory.getOrDefault(productId, 0) + count);
        repository.writeToJson(inventory);
    }

    /**
     * Retrieves the stock level for a specified product.
     *
     * @param productId the unique identifier of the product.
     * @return the stock level for the product, or {@code 0} if the product is not found in the inventory.
     */
    public int getStockForProductId(UUID productId) {
        return getInventory().getOrDefault(productId, 0);
    }

    /**
     * Reduces the stock level for a specified product by one unit.
     * <p>
     * This operation only succeeds if the current stock level for the product is greater than zero.
     * The updated inventory is saved to the repository.
     * </p>
     *
     * @param productId the unique identifier of the product.
     * @return {@code true} if the stock was successfully reduced, {@code false} otherwise.
     */
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