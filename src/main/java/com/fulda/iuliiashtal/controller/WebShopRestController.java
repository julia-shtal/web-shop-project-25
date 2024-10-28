package com.fulda.iuliiashtal.controller;

import com.fulda.iuliiashtal.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WebShopRestController {

    private static final List<Product> products = Arrays.asList(
            new Product(1L, "Hat", 12.99, "S", "Yellow", "A casual yellow hat.", "Accessories"),
            new Product(2L, "T-Shirt", 9.99, "S", "White", "A comfortable white T-shirt.", "Apparel"),
            new Product(3L, "Gloves", 5.99, "M", "Blue", "Stylish blue gloves.", "Accessories"),
            new Product(4L, "Jacket", 89.99, "L", "Black", "Warm black jacket.", "Outerwear"),
            new Product(5L, "Sneakers", 69.99, "44", "Red", "Trendy red sneakers.", "Footwear")

    );

    @GetMapping("/")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductDetail(@PathVariable Long id) {
        return getProductById(id).orElse(null);
    }

    private Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @GetMapping("/products/color")
    public List<Product> getProductsByColor(@RequestParam String color) {
        return products.stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
