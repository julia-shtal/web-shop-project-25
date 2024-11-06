package com.fulda.iuliiashtal.product.controller;

import com.fulda.iuliiashtal.product.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Product getProductDetail(@PathVariable UUID id) {
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

    @PostMapping("api/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (productService.checkExists(product)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @PutMapping("api/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<List<Product>> delete(@PathVariable UUID id) {
        boolean isDeleted = productService.delete(id);
        if (isDeleted) {
            List<Product> remainingProducts = productService.getProducts();
            return ResponseEntity.ok(remainingProducts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
