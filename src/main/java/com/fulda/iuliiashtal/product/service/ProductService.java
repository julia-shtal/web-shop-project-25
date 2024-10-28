package com.fulda.iuliiashtal.product.service;

import com.fulda.iuliiashtal.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final List<Product> products = Arrays.asList(
            new Product(1L, "Hat", 12.99, "S", "Yellow", "A casual yellow hat.", "Accessories"),
            new Product(2L, "T-Shirt", 9.99, "S", "White", "A comfortable white T-shirt.", "Apparel"),
            new Product(3L, "Gloves", 5.99, "M", "Blue", "Stylish blue gloves.", "Accessories"),
            new Product(4L, "Jacket", 89.99, "L", "Black", "Warm black jacket.", "Outerwear"),
            new Product(5L, "Sneakers", 69.99, "44", "Red", "Trendy red sneakers.", "Footwear")
    );

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public List<Product> getProductsByColor(String color) {
        return products.stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Product>getProductsByCategory(String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
