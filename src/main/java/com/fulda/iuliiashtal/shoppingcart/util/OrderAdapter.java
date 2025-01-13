package com.fulda.iuliiashtal.shoppingcart.util;

import com.fulda.iuliiashtal.notification.service.EMailService;
import com.fulda.iuliiashtal.shoppingcart.facade.OrderFacade;
import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderAdapter {
    private final OrderFacade orderFacade;
    private final EMailService emailService;

    public Order finalizeOrder(BigDecimal totalPrice) {
        Order order = orderFacade.finalizeOrder(totalPrice);
        emailService.sendEmail(order.getUserId());
        return order;
    }
}
