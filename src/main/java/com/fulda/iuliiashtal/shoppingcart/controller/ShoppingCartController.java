package com.fulda.iuliiashtal.shoppingcart.controller;

import com.fulda.iuliiashtal.product.model.enums.Currency;
import com.fulda.iuliiashtal.shoppingcart.facade.ShoppingCartFacade;
import com.fulda.iuliiashtal.shoppingcart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * Controller for managing shopping cart operations.
 * This controller provides endpoints for viewing the cart, adding products to the cart,
 * and calculating totals for individual products and the entire cart.
 */
@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartFacade cartFacade;
    private final ShoppingCartService cartService;

    /**
     * Displays the current state of the shopping cart.
     *
     * @param model the {@link Model} object to pass data to the view.
     *              This includes the shopping cart object to be rendered on the page.
     * @return the name of the Thymeleaf view template ("cart") to render the shopping cart page.
     */
    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cartFacade.getCart());
        model.addAttribute("voucherPercentage", cartFacade.getVoucherPercentage());
        return "cart";
    }

    @PostMapping("/cart/voucher")
    public String applyVoucher() {
        cartFacade.applyNewVoucher();
        return "redirect:/cart";
    }

    @PostMapping("/cart/removevoucher")
    public String removeVoucher() {
        cartFacade.removeVoucher();
        return "redirect:/cart";
    }

    @GetMapping("/cart/convert")
    public String convertToDollar(@RequestParam("targetCurrency") String targetCurrency) {
        cartFacade.convertCurrency(Currency.valueOf(targetCurrency));
        return "redirect:/cart";
    }

    /**
     * Adds a product to the shopping cart.
     *
     * @param id the {@link UUID} of the product to be added to the cart.
     * @return a redirection to the shopping cart page.
     * If the product cannot be added due to insufficient stock,
     * the redirection URL includes an error parameter (?error=out-of-stock).
     */
    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable UUID id) {
        boolean added = cartFacade.addToCart(id);
        if (!added) {
            return "redirect:/cart?error=out-of-stock";
        }
        return "redirect:/cart";
    }

    /**
     * Calculates the total price for a specific product based on its price and quantity.
     *
     * @param price    the unit price of the product, represented as {@link BigDecimal}.
     * @param quantity the quantity of the product, represented as an integer.
     * @return a {@link Map} containing a single key-value pair where the key is "total"
     * and the value is the calculated total price as a {@link BigDecimal}.
     */
    @GetMapping("/calculate-product-total")
    @ResponseBody
    public Map<String, BigDecimal> calculateTotalForProduct(@RequestParam BigDecimal price, @RequestParam int quantity) {
        BigDecimal total = cartFacade.getTotalPriceForCurrentProduct(price, quantity);
        return Collections.singletonMap("total", total);
    }

    /**
     * Calculates the total price for all items in the shopping cart.
     *
     * @return a {@link Map} containing a single key-value pair where the key is "total"
     * and the value is the total price of all items in the cart as a {@link BigDecimal}.
     */
    @GetMapping("/calculate-cart-total")
    @ResponseBody
    public Map<String, BigDecimal> calculateCartTotal() {
        BigDecimal total = cartFacade.getTotalPrice();
        return Collections.singletonMap("total", total);
    }
}