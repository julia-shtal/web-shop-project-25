package com.fulda.iuliiashtal.shoppingcart.service;

import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * The {@code OrderService} class provides business logic for handling orders in the shopping cart system.
 * It is responsible for operations such as finalizing an order by associating it with a specific user
 * and total price.
 *
 * <p><b>Note:</b> For 3) in exercise sheet: If I use here UserService and this service - in UserService, I will receive this exception:
 * BeanCurrentlyInCreationException: Error creating bean with name 'orderService':
 * Requested bean is currently in creation: Is there an unresolvable circular reference?
 *
 * @see Order
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    /**
     * Finalizes an order by creating a new {@link Order} object with the provided total price and user ID.
     *
     * <p>This method encapsulates the logic for creating an order and associates it with the given user.
     * It is a simple operation and does not persist the order, leaving that responsibility to a repository
     * or other persistence mechanism.
     *
     * @param totalPrice the total price of the order
     * @param userId the unique identifier of the user placing the order
     * @return a new {@link Order} object containing the total price and user ID
     * @throws IllegalArgumentException if the totalPrice is null or less than zero, or if the userId is null
     */
    public Order finalizeOrder(BigDecimal totalPrice, UUID userId) {
        return new Order(totalPrice, userId);
    }
}