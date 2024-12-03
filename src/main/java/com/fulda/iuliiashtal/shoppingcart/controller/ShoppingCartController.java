package com.fulda.iuliiashtal.shoppingcart.controller;

import com.fulda.iuliiashtal.shoppingcart.facade.ShoppingCartFacade;
import com.fulda.iuliiashtal.shoppingcart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * Controller for managing shopping cart operations.
 * Handles requests related to viewing and modifying the shopping cart.
 */
@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartFacade shoppingCartFacade;
    private final ShoppingCartService cartService;

    /**
     * Displays the current state of the shopping cart.
     *
     * @param model the {@link Model} object to pass data to the view.
     * @return the name of the view template ("cart") to render the shopping cart page.
     */
    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    /**
     * Adds a product to the shopping cart.
     *
     * @param id the {@link UUID} of the product to add to the cart.
     * @return a redirection to the cart page. If the product is out of stock, an error query parameter is included in the URL.
     */
    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable UUID id) {
        boolean added = shoppingCartFacade.addToCart(id);
        if (!added) {
            return "redirect:/cart?error=out-of-stock";
        }
        return "redirect:/cart";
    }
}