package com.fulda.iuliiashtal.shoppingcart.model.entity;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.model.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a shopping cart that holds a collection of products and their quantities.
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
    private BigDecimal originalTotalPrice = BigDecimal.valueOf(0.0);
    private BigDecimal effectiveTotalPrice = BigDecimal.valueOf(0.0);
    private boolean voucherApplied;
    private Currency currency;
}


