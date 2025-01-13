package com.fulda.iuliiashtal.shoppingcart.controller;

import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import com.fulda.iuliiashtal.shoppingcart.util.OrderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderAdapter orderAdapter;

    @PostMapping("/checkout")
    public String checkout(@RequestParam("totalPrice") BigDecimal totalPrice, Model model) {
        Order order = orderAdapter.finalizeOrder(totalPrice);
        model.addAttribute("order", order);
        return "order-success";
    }
}