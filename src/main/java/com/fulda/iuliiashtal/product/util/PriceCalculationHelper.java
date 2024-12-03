package com.fulda.iuliiashtal.product.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service for handling price-related calculations and rounding.
 *
 * This service centralizes price rounding logic to ensure consistent rounding
 * behavior throughout the application. It rounds prices to two decimal places
 * using {@link RoundingMode#HALF_UP}, which rounds up from 0.5. The service is
 * intended to be reused wherever price rounding or related calculations are needed.
 *
 * Use cases include:
 * <ul>
 *     <li>Calculating the total price of a shopping cart.</li>
 *     <li>Displaying product prices for quantities > 1.</li>
 *     <li>Rounding prices after currency conversion or voucher application.</li>
 * </ul>
 *
 */
public class PriceCalculationHelper {

    private static final int SCALE = 2; // Number of decimal places to round to

    /**
     * Rounds the given price to two decimal places using {@link RoundingMode#HALF_UP}.
     *
     * @param price The original price as a {@link BigDecimal}.
     * @return The rounded price as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the input price is null.
     */
    public static BigDecimal roundPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null.");
        }
        return price.setScale(SCALE, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the total price of a product based on its unit price and quantity.
     * The total price is rounded to two decimal places.
     *
     * @param unitPrice The price of a single unit as a {@link BigDecimal}.
     * @param quantity  The quantity of the product.
     * @return The total rounded price as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the unitPrice is null or the quantity is negative.
     */
    public static BigDecimal calculateTotalPrice(BigDecimal unitPrice, int quantity) {
        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price cannot be null.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return roundPrice(totalPrice);
    }
}
