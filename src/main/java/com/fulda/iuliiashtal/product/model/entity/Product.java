package com.fulda.iuliiashtal.product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a product in the system.
 * This entity is used to store and manage product data in the database.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Product.fetchProductsByColor", query = "SELECT e from Product e where e.color = ?1")
@NamedQuery(name = "Product.fetchProductsByName", query = "SELECT e from Product e where e.name = ?1")
@NamedQuery(name = "Product.fetchProductsByCategory", query = "SELECT e from Product e where e.category = ?1")
public class Product {

    /**
     * The unique identifier of the product, automatically generated.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     */
    private BigDecimal price;

    /**
     * The size of the product (e.g., small, medium, large).
     */
    private String size;

    /**
     * The color of the product.
     */
    private String color;

    /**
     * A description of the product.
     */
    private String description;

    /**
     * The category of the product (e.g., outwear, footwear).
     */
    private String category;

    /**
     * Constructor to initialize a product with specified values, excluding the ID.
     *
     * @param name        the name of the product.
     * @param price       the price of the product.
     * @param size        the size of the product.
     * @param color       the color of the product.
     * @param description the description of the product.
     * @param category    the category of the product.
     */
    public Product(String name, double price, String size, String color, String description, String category) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.size = size;
        this.color = color;
        this.description = description;
        this.category = category;
    }

    /**
     * Compares this product to another object for equality.
     * Two products are considered equal if they have the same ID, name, price, size, color, description, and category.
     *
     * @param o the object to compare to.
     * @return true if the products are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(price, product.price) &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(size, product.size) &&
                Objects.equals(color, product.color) &&
                Objects.equals(description, product.description) &&
                Objects.equals(category, product.category);
    }

    /**
     * Computes a hash code for this product based on its attributes.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, size, color, description, category);
    }
}
