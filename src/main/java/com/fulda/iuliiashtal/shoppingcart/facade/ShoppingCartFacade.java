package com.fulda.iuliiashtal.shoppingcart.facade;

import com.fulda.iuliiashtal.inventory.service.InventoryService;
import com.fulda.iuliiashtal.shoppingcart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Facade class that provides a simplified interface for managing shopping cart operations
 * while coordinating with the inventory service.
 * <p>
 * This class is annotated as a Spring {@link Service} and leverages {@link RequiredArgsConstructor}
 * for dependency injection of required services.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ShoppingCartFacade {

    /**
     * Service responsible for managing inventory operations.
     */
    private final InventoryService inventoryService;

    /**
     * Service responsible for managing shopping cart operations.
     */
    private final ShoppingCartService shoppingCartService;

    /**
     * Adds a product to the shopping cart by its ID. Before adding the product,
     * it checks and reduces the stock for the product in the inventory.
     *
     * @param productId the unique identifier of the product to add to the cart.
     * @return {@code true} if the product was successfully added to the cart,
     * {@code false} if there was insufficient stock.
     */
    public boolean addToCart(UUID productId) {
        if (inventoryService.reduceStockForProductId(productId)) {
            shoppingCartService.addProductByIdToCart(productId);
            return true;
        }
        return false;
    }
}
