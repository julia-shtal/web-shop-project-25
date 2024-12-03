package com.fulda.iuliiashtal.product.repository;

import com.fulda.iuliiashtal.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing {@link Product} entities.
 * This interface provides methods for performing CRUD operations and custom queries on the product database.
 * It extends {@link JpaRepository}, which includes built-in support for common database operations.
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Fetches a list of products filtered by their color.
     *
     * @param color the color of the products to retrieve.
     * @return a list of {@link Product} objects that match the specified color.
     */
    List<Product> fetchProductsByColor(String color);

    /**
     * Fetches a list of products filtered by their name.
     *
     * @param name the name of the products to retrieve.
     * @return a list of {@link Product} objects that match the specified name.
     */
    List<Product> fetchProductsByName(String name);

    /**
     * Fetches a list of products filtered by their category.
     *
     * @param category the category of the products to retrieve.
     * @return a list of {@link Product} objects that belong to the specified category.
     */
    List<Product> fetchProductsByCategory(String category);
}
