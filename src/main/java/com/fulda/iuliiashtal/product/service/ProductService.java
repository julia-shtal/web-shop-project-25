package com.fulda.iuliiashtal.product.service;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for managing {@link Product} entities.
 * This class provides methods to perform CRUD operations and custom logic for products.
 * It interacts with the {@link ProductRepository} to handle data persistence.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of all {@link Product} entities.
     */
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * Retrieves products in a paginated format.
     *
     * @param pageable a {@link Pageable} object specifying pagination details.
     * @return a {@link Page} of {@link Product} entities.
     */
    public Page<Product> getAllPagedProducts(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the {@link UUID} of the product to retrieve.
     * @return the {@link Product} entity with the given ID.
     * @throws java.util.NoSuchElementException if the product with the given ID is not found.
     */
    public Product getProductById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    /**
     * Retrieves products filtered by color.
     *
     * @param color the color of the products to retrieve.
     * @return a list of {@link Product} entities matching the specified color.
     */
    public List<Product> getProductsByColor(String color) {
        return repository.fetchProductsByColor(color);
    }

    /**
     * Retrieves products filtered by name.
     *
     * @param name the name of the products to retrieve.
     * @return a list of {@link Product} entities matching the specified name.
     */
    public List<Product> getProductsByName(String name) {
        return repository.fetchProductsByName(name);
    }

    /**
     * Retrieves products filtered by category.
     *
     * @param category the category of the products to retrieve.
     * @return a list of {@link Product} entities matching the specified category.
     */
    public List<Product> getProductsByCategory(String category) {
        return repository.fetchProductsByCategory(category);
    }

    /**
     * Creates a new product.
     *
     * @param product the {@link Product} entity to save.
     * @return the newly created {@link Product}.
     */
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product the {@link Product} entity with updated data.
     * @return the updated {@link Product}, or {@code null} if the product with the given ID does not exist.
     */
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

    /**
     * Deletes a product by its ID.
     *
     * @param id the {@link UUID} of the product to delete.
     * @return {@code true} if the product was successfully deleted, {@code false} if the product was not found.
     */
    public boolean deleteProduct(UUID id) {
        Optional<Product> foundProduct = repository.findById(id);
        if (foundProduct.isPresent()) {
            repository.delete(foundProduct.get());
            return true;
        }
        return false;
    }
}