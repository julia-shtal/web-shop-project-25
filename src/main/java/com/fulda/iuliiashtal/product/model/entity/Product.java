package com.fulda.iuliiashtal.product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Product.fetchProductsByColor", query = "SELECT e from Product e where e.color = ?1")
@NamedQuery(name = "Product.fetchProductsByName", query = "SELECT e from Product e where e.name = ?1")
@NamedQuery(name = "Product.fetchProductsByCategory", query = "SELECT e from Product e where e.category = ?1")
public class Product {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private double price;
    private String size;
    private String color;
    private String description;
    private String category;

    public Product(String name, double price, String size, String color, String description, String category) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.description = description;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(size, product.size) &&
                Objects.equals(color, product.color) &&
                Objects.equals(description, product.description) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, size, color, description, category);
    }
}
