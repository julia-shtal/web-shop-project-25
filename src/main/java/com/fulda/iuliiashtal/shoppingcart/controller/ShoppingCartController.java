package com.fulda.iuliiashtal.shoppingcart.controller;

import com.fulda.iuliiashtal.shoppingcart.facade.ShoppingCartFacade;
import com.fulda.iuliiashtal.shoppingcart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartFacade shoppingCartFacade;
    private final ShoppingCartService cartService;


    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable UUID id) {
        boolean added = shoppingCartFacade.addToCart(id);
        if (!added) {
            return "redirect:/cart?error=out-of-stock";
        }
        return "redirect:/cart";
    }

}

