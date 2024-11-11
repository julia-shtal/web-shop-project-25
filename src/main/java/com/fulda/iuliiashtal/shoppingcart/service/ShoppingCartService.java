package com.fulda.iuliiashtal.shoppingcart.service;

import com.fulda.iuliiashtal.product.entity.Product;
import com.fulda.iuliiashtal.shoppingcart.entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCart cart = new ShoppingCart();

    public ShoppingCart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        Integer integer = cart.getProducts().get(product);
        int i = cart.getProducts().getOrDefault(product, 0) + 1;
        cart.getProducts().put(product, i);
    }
}

