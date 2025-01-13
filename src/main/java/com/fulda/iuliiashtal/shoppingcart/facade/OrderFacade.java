package com.fulda.iuliiashtal.shoppingcart.facade;

import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import com.fulda.iuliiashtal.shoppingcart.service.OrderService;
import com.fulda.iuliiashtal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final UserService userService;

    public Order finalizeOrder(BigDecimal totalPrice) {
        UUID userId = userService.getUserId();
        return orderService.finalizeOrder(totalPrice, userId);
    }
}
