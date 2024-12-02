package com.fulda.iuliiashtal.shoppingcart.entity;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.service.PriceCalculationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Map<Product, Integer> products = new HashMap<>();

    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(products.entrySet().stream()
                .mapToDouble(entry -> PriceCalculationService.calculateTotalPrice(entry.getKey().getPrice(), entry.getValue()).doubleValue())
                .sum());
    }
}

