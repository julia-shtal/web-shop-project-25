package com.fulda.iuliiashtal.product.util;

import com.fulda.iuliiashtal.product.model.enums.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service for handling price-related calculations and rounding.
 * <p>
 * This service centralizes price rounding logic to ensure consistent rounding
 * behavior throughout the application. It rounds prices to two decimal places
 * using {@link RoundingMode#HALF_UP}, which rounds up from 0.5. The service is
 * intended to be reused wherever price rounding or related calculations are needed.
 * <p>
 * Use cases include:
 * <ul>
 *     <li>Calculating the total price of a shopping cart.</li>
 *     <li>Displaying product prices for quantities > 1.</li>
 *     <li>Rounding prices after currency conversion or voucher application.</li>
 * </ul>
 */
@Service
public class PriceCalculationService {

    private final BigDecimal voucherPercentage;
    private final String defaultCurrency;

    public PriceCalculationService(@Value("${app.discount.percentage}") String voucherPercentage, @Value("${app.currency.default}") String
                                           defaultCurrency) {
        this.voucherPercentage = new BigDecimal(voucherPercentage);
        this.defaultCurrency = defaultCurrency;
    }

    public Currency getDefaultCurrency() {
        return Currency.valueOf(defaultCurrency);
    }

    public BigDecimal getVoucherPercentage() {
        return voucherPercentage;
    }

    /**
     * Rounds the given price to two decimal places using {@link RoundingMode#HALF_UP}.
     *
     * @param price The original price as a {@link BigDecimal}.
     * @return The rounded price as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the input price is null.
     */
    public BigDecimal roundPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null.");
        }
        // Number of decimal places to round to
        int SCALE = 2;
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
    public BigDecimal calculateTotalPrice(BigDecimal unitPrice, int quantity) {
        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price cannot be null.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return roundPrice(totalPrice);
    }

    public BigDecimal convertCurrencies(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null.");
        }
        if (fromCurrency == toCurrency) {
            throw new IllegalArgumentException("Currencies must be different for conversion.");
        }
        // Hardcoded conversion rates (1 Euro = 1.1 Dollars, 1 Dollar = 0.91 Euros)
        BigDecimal conversionRate;
        if (fromCurrency == Currency.EURO && toCurrency == Currency.DOLLAR) {
            conversionRate = BigDecimal.valueOf(1.1);
        } else if (fromCurrency == Currency.DOLLAR && toCurrency == Currency.EURO) {
            conversionRate = BigDecimal.valueOf(0.91);
        } else {
            throw new IllegalArgumentException("Unsupported currency conversion.");
        }
        BigDecimal convertedAmount = amount.multiply(conversionRate);
        return roundPrice(convertedAmount);
    }

    public BigDecimal applyPercentageVoucher(BigDecimal price, BigDecimal percentage) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-null and non-negative.");
        }
        if (percentage == null || percentage.compareTo(BigDecimal.ZERO) < 0 || percentage.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100.");
        }
        BigDecimal discount = price.multiply(percentage).divide(BigDecimal.valueOf(100));
        BigDecimal discountedPrice = price.subtract(discount);
        return roundPrice(discountedPrice);
    }
}
