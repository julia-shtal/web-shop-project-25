package com.fulda.iuliiashtal.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String color;
    private String category;
    private String size;
    private int stock;
    private boolean isSoldOut;
}
