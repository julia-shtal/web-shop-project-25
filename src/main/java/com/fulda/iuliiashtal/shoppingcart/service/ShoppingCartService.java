package com.fulda.iuliiashtal.shoppingcart.service;

import com.fulda.iuliiashtal.product.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import com.fulda.iuliiashtal.shoppingcart.entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ProductService productService;
    private final ShoppingCart cart = new ShoppingCart();

    public ShoppingCart getCart() {
        return cart;
    }

    public void addProductByIdToCart(UUID id) {
        Product product = productService.getProductById(id);
        int value = cart.getProducts().getOrDefault(product, 0) + 1;
        cart.getProducts().put(product, value);
    }
}

