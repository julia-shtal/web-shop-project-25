package com.fulda.iuliiashtal.product.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private String size;
    private String color;
    private String description;
    private String category;
}
