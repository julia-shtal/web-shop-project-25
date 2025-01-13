package com.fulda.iuliiashtal.shoppingcart.service;

import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    public Order finalizeOrder(BigDecimal totalPrice, UUID userId) {
        return new Order(totalPrice, userId);
    }
}