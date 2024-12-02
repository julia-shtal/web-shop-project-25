package com.fulda.iuliiashtal.shoppingcart.entity;

import com.fulda.iuliiashtal.product.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Map<Product, Integer> products = new HashMap<>();

    public double getTotalPrice() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice().doubleValue() * entry.getValue())
                .sum();
    }
}

