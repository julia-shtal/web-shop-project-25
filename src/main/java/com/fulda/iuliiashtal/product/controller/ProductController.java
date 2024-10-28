package com.fulda.iuliiashtal.product.controller;

import com.fulda.iuliiashtal.product.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping("api/products")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("api/products/{id}")
    public Product getProductDetail(@PathVariable Long id) {
        return productService.getProductById(id).orElse(null);
    }

    @GetMapping("api/products/color")
    public List<Product> getProductsByColor(@RequestParam String color) {
        return productService.getProductsByColor(color);
    }

    @GetMapping("api/products/category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }
}
