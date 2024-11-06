package com.fulda.iuliiashtal.product.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private double price;
    private String size;
    private String color;
    private String description;
    private String category;

    public Product(String name, String description, double price) {
        this.setId(UUID.randomUUID());
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
    }
}
