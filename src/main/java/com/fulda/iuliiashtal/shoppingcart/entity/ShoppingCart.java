package com.fulda.iuliiashtal.shoppingcart.entity;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.util.PriceCalculationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a shopping cart that holds a collection of products and their quantities.
 * Provides functionality to calculate the total price of the items in the cart.
 * <p>
 * This class uses Lombok annotations for generating boilerplate code like getters, setters,
 * constructors, and default initialization of the products map.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    /**
     * A map storing the products in the shopping cart and their quantities.
     * The key is a {@link Product} object, and the value is an {@link Integer}
     * representing the quantity of that product.
     */
    private Map<Product, Integer> products = new HashMap<>();

    /**
     * Calculates the total price of all items in the shopping cart.
     * Each product's total price is determined by multiplying its unit price
     * by its quantity, using the {@link PriceCalculationService}.
     *
     * @return the total price of all products in the cart as a {@link BigDecimal}.
     */
    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(products.entrySet().stream()
                .mapToDouble(entry -> PriceCalculationService
                        .calculateTotalPrice(entry.getKey().getPrice(), entry.getValue())
                        .doubleValue())
                .sum());
    }
}


