package com.fulda.iuliiashtal.shoppingcart.facade;

import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import com.fulda.iuliiashtal.shoppingcart.service.OrderService;
import com.fulda.iuliiashtal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * <p>The main responsibility of this class is to handle the process of finalizing an order,
 * combining data from the {@link OrderService} and {@link UserService}.
 *
 * @see Order
 * @see OrderService
 * @see UserService
 */
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final UserService userService;

    /**
     * Finalizes an order by calculating the total price and associating it with the user ID
     * of the currently authenticated user.
     *
     * <p>This method delegates the core business logic to the {@link OrderService} and retrieves
     * the user ID from the {@link UserService}.
     *
     * @param totalPrice the total price of the order to be finalized
     * @return the finalized {@link Order} object
     * @throws IllegalArgumentException if the totalPrice is null or less than zero
     */
    public Order finalizeOrder(BigDecimal totalPrice) {
        UUID userId = userService.getUserId();
        return orderService.finalizeOrder(totalPrice, userId);
    }
}
