package com.fulda.iuliiashtal.shoppingcart.service;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.shoppingcart.model.entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing the shopping cart and its associated operations.
 * <p>
 * Provides methods to access the shopping cart and to add products to it.
 * This class ensures that the shopping cart is maintained as a single instance,
 * representing the user's current session.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    /**
     * The shopping cart instance managed by this service.
     * <p>
     * This instance holds the current state of the user's shopping cart,
     * including the products added and their quantities.
     * </p>
     */
    private final ShoppingCart cart = new ShoppingCart();

    /**
     * Retrieves the current shopping cart instance.
     * <p>
     * This method provides access to the shopping cart for operations such as
     * viewing its contents or calculating the total price of items.
     * </p>
     *
     * @return the {@link ShoppingCart} instance containing the user's cart data.
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Adds a product to the shopping cart.
     * <p>
     * If the product already exists in the cart, its quantity is incremented by one.
     * If the product does not exist in the cart, it is added with an initial quantity of one.
     * </p>
     *
     * @param product the {@link Product} to be added to the shopping cart.
     */
    public void addProductByIdToCart(Product product) {
        int currentQuantity = cart.getProducts().getOrDefault(product, 0);
        cart.getProducts().put(product, currentQuantity + 1);
    }
}