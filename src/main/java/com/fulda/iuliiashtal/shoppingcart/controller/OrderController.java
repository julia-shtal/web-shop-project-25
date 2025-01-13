package com.fulda.iuliiashtal.shoppingcart.controller;

import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import com.fulda.iuliiashtal.shoppingcart.util.OrderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * The {@code OrderController} class handles web requests related to order processing in the shopping cart system.
 * It acts as the entry point for user interactions, providing endpoints to manage orders.
 *
 * <p>The main responsibility of this class is to handle the checkout process, finalizing the order and
 * preparing data for the corresponding view.
 *
 * @see OrderAdapter
 * @see Order
 */
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderAdapter orderAdapter;

    /**
     * Handles the checkout process by finalizing an order and preparing the success view.
     *
     * <p>This method processes a POST request to the {@code /checkout} endpoint, retrieves the total price
     * from the request parameters, and delegates order finalization to the {@link OrderAdapter}.
     * The finalized {@link Order} object is then added to the model for rendering in the success view.
     *
     * @param totalPrice the total price of the order submitted by the user
     * @param model      the {@link Model} object to carry attributes for the view
     * @return the name of the view to be rendered, {@code "order-success"}
     * @throws IllegalArgumentException if the {@code totalPrice} is null or less than zero
     */
    @PostMapping("/checkout")
    public String checkout(@RequestParam("totalPrice") BigDecimal totalPrice, Model model) {
        Order order = orderAdapter.finalizeOrder(totalPrice);
        model.addAttribute("order", order);
        return "order-success";
    }
}
