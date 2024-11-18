package com.fulda.iuliiashtal.shoppingcart.facade;

import com.fulda.iuliiashtal.inventory.service.InventoryService;
import com.fulda.iuliiashtal.shoppingcart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartFacade {

    private final InventoryService inventoryService;
    private final ShoppingCartService shoppingCartService;

    public boolean addToCart(UUID productId) {
        if (inventoryService.reduceStockForProductId(productId)) {
            shoppingCartService.addProductByIdToCart(productId);
            return true;
        }
        return false;
    }
}
