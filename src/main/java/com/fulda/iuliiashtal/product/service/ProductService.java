package com.fulda.iuliiashtal.product.service;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Product> getProductsByColor(String color) {
        return repository.fetchProductsByColor(color);
    }

    public List<Product> getProductsByName(String name) {

        return repository.fetchProductsByName(name);
    }

    public List<Product> getProductsByCategory(String category) {
        return repository.fetchProductsByCategory(category);
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Product product) {
        Optional<Product> optional = repository.findById(product.getId());
        if (optional.isEmpty()) {
            return null;
        }
        Product existingProduct = optional.get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setSize(product.getSize());
        existingProduct.setColor(product.getColor());
        existingProduct.setCategory(product.getCategory());
        return repository.save(existingProduct);
    }

    public boolean deleteProduct(UUID id) {
        Optional<Product> foundProduct = repository.findById(id);
        if (foundProduct.isPresent()) {
            repository.delete(foundProduct.get());
            return true;
        }
        return false;
    }
}
