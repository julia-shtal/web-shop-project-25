package com.fulda.iuliiashtal.shoppingcart.service;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import com.fulda.iuliiashtal.shoppingcart.entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for managing the shopping cart and its operations.
 * <p>
 * This class provides methods to access the shopping cart and add products by their ID.
 * It interacts with the {@link ProductService} to retrieve product details.
 * </p>
 *
 * This class is annotated as a Spring {@link Service} and uses {@link RequiredArgsConstructor}
 * for dependency injection.
 */
@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    /**
     * Service responsible for retrieving product details.
     */
    private final ProductService productService;

    /**
     * The shopping cart associated with this service.
     */
    private final ShoppingCart cart = new ShoppingCart();

    /**
     * Retrieves the current shopping cart.
     *
     * @return the {@link ShoppingCart} instance managed by this service.
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Adds a product to the shopping cart by its unique identifier.
     * <p>
     * The method fetches the product details using the {@link ProductService} and
     * increments the quantity of the product in the shopping cart. If the product
     * is not already in the cart, it is added with a quantity of one.
     * </p>
     *
     * @param id the unique identifier of the product to be added.
     */
    public void addProductByIdToCart(UUID id) {
        Product product = productService.getProductById(id);
        int value = cart.getProducts().getOrDefault(product, 0) + 1;
        cart.getProducts().put(product, value);
    }
}
