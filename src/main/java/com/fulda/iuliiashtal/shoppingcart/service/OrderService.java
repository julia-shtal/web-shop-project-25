package com.fulda.iuliiashtal.shoppingcart.service;

import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    //For 3) in exercise sheet: If I use here UserService and this service - in UserService, I will receive this exception:
    //BeanCurrentlyInCreationException: Error creating bean with name 'orderService':
    //Requested bean is currently in creation: Is there an unresolvable circular reference?
    public Order finalizeOrder(BigDecimal totalPrice, UUID userId) {
        return new Order(totalPrice, userId);
    }
}