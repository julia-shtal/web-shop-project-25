package com.fulda.iuliiashtal.product.repository;

import com.fulda.iuliiashtal.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> fetchProductsByColor(String color);
    List<Product> fetchProductsByName(String name);
    List<Product> fetchProductsByCategory(String category);
}
