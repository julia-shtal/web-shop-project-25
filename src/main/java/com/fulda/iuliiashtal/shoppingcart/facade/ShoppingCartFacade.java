package com.fulda.iuliiashtal.shoppingcart.facade;

import com.fulda.iuliiashtal.inventory.service.InventoryService;
import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.model.enums.Currency;
import com.fulda.iuliiashtal.product.service.ProductService;
import com.fulda.iuliiashtal.product.util.PriceCalculationService;
import com.fulda.iuliiashtal.shoppingcart.model.entity.ShoppingCart;
import com.fulda.iuliiashtal.shoppingcart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Facade class providing a simplified interface for shopping cart operations.
 * This class coordinates interactions between the shopping cart, inventory, product,
 * and pricing services, ensuring business logic is centralized and consistent.
 * <p>
 * Annotated as a Spring {@link Service}, this class is instantiated as a singleton
 * and utilizes {@link RequiredArgsConstructor} for dependency injection.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ShoppingCartFacade {

    /**
     * Service responsible for managing inventory operations,
     * such as checking and reducing product stock.
     */
    private final InventoryService inventoryService;

    /**
     * Service managing shopping cart data, including adding, removing, and
     * retrieving products from the cart.
     */
    private final ShoppingCartService cartService;

    /**
     * Service for retrieving product details, such as pricing and availability.
     */
    private final ProductService productService;

    /**
     * Utility service for calculating product prices, including total costs
     * based on quantity.
     */
    private final PriceCalculationService priceCalculationService;

    public ShoppingCart getCart() {
        ShoppingCart cart = cartService.getCart();
        //cart.setOriginalTotalPrice(getTotalPrice());
        return cart;
    }

    public void convertCurrency(Currency targetCurrency) {
        if (getCart().getCurrency() != targetCurrency) {
            getCart().setOriginalTotalPrice(
                    priceCalculationService.convertCurrencies(getCart().getOriginalTotalPrice(), getCart().getCurrency(), targetCurrency));
            getCart().setEffectiveTotalPrice(
                    priceCalculationService.convertCurrencies(getCart().getEffectiveTotalPrice(), getCart().getCurrency(), targetCurrency));
            getCart().setCurrency(targetCurrency);
        }
    }

    /**
     * Adds a product to the shopping cart by its unique identifier.
     * Before adding, the method checks inventory availability and reduces stock
     * if the product is in stock.
     *
     * @param productId the {@link UUID} of the product to add.
     * @return {@code true} if the product was successfully added to the cart;
     * {@code false} if there is insufficient stock.
     */
    public boolean addToCart(UUID productId) {
        if (inventoryService.reduceStockForProductId(productId)) {
            Product product = productService.getProductById(productId);
            cartService.addProductByIdToCart(product);
            cartService.getCart().setOriginalTotalPrice(getTotalPrice());
            applyVoucherForExistingCart();
            return true;
        }
        return false;
    }

    /**
     * Computes the total price of all products currently in the shopping cart.
     * The total is calculated by summing the cost of each product, where the
     * cost is determined by multiplying the product's unit price by its quantity.
     * <p>
     * Uses the {@link PriceCalculationService} to ensure consistent price calculations.
     *
     * @return the total price of all items in the cart as a {@link BigDecimal}.
     */
    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(cartService.getCart().getProducts().entrySet().stream()
                .mapToDouble(entry -> priceCalculationService.calculateTotalPrice(entry.getKey().getPrice(), entry.getValue()).doubleValue())
                .sum());
    }

    /**
     * Computes the total price for a specific product based on its unit price and quantity.
     * Uses the {@link PriceCalculationService} to perform the calculation.
     *
     * @param price    the unit price of the product as a {@link BigDecimal}.
     * @param quantity the quantity of the product as an integer.
     * @return the total price for the specified product as a {@link BigDecimal}.
     */
    public BigDecimal getTotalPriceForCurrentProduct(BigDecimal price, int quantity) {
        return priceCalculationService.calculateTotalPrice(price, quantity);
    }

    public BigDecimal getVoucherPercentage() {
        return PriceCalculationService.VOUCHER_PERCENTAGE;
    }

    public void applyNewVoucher() {
        if (!cartService.getCart().isVoucherApplied()) {
            applyVoucher();
        }
    }

    public void applyVoucherForExistingCart() {
        if (cartService.getCart().isVoucherApplied()) {
            applyVoucher();
        }
    }

    private void applyVoucher() {
        BigDecimal discountedTotal = priceCalculationService.applyPercentageVoucher(cartService.getCart().getOriginalTotalPrice(),
                getVoucherPercentage());
        cartService.setEffectiveTotalPrice(discountedTotal, true);
    }

    public void removeVoucher() {
        if (cartService.getCart().isVoucherApplied()) {
            cartService.setEffectiveTotalPrice(cartService.getCart().getOriginalTotalPrice(), false);
        }
    }
}