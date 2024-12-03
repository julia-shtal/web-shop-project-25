package com.fulda.iuliiashtal.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for representing detailed product information.
 * <p>
 * This class provides a structured format for transmitting product details
 * between different layers of the application, such as from the service layer
 * to the presentation layer.
 * </p>
 *
 * The class uses Lombok annotations for generating boilerplate code like getters,
 * setters, and constructors.
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductDetailDTO {

    /**
     * Unique identifier for the product.
     */
    private UUID id;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Detailed description of the product.
     */
    private String description;

    /**
     * Price of the product as a {@link BigDecimal}.
     */
    private BigDecimal price;

    /**
     * Color of the product.
     */
    private String color;

    /**
     * Category to which the product belongs.
     */
    private String category;

    /**
     * Size of the product (e.g., S, M, L and other).
     */
    private String size;

    /**
     * Number of items of this product currently in stock.
     */
    private int stock;

    /**
     * Indicates whether the product is sold out.
     * <p>
     * This is {@code true} if the product is out of stock, {@code false} otherwise.
     * </p>
     */
    private boolean isSoldOut;
}
