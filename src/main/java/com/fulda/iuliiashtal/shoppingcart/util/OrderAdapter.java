package com.fulda.iuliiashtal.shoppingcart.util;

import com.fulda.iuliiashtal.notification.service.EMailService;
import com.fulda.iuliiashtal.shoppingcart.facade.OrderFacade;
import com.fulda.iuliiashtal.shoppingcart.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * The {@code OrderAdapter} class acts as a utility layer that coordinates order finalization and
 * related operations, such as sending notifications. It integrates the functionality provided by
 * {@link OrderFacade} and {@link EMailService}.
 *
 * <p>The main responsibility of this class is to streamline the process of finalizing an order by:
 * <ul>
 *     <li>Delegating the order finalization logic to {@link OrderFacade}</li>
 *     <li>Sending a notification email to the user via {@link EMailService}</li>
 * </ul>
 *
 * @see OrderFacade
 * @see EMailService
 * @see Order
 */
@Service
@RequiredArgsConstructor
public class OrderAdapter {

    private final OrderFacade orderFacade;
    private final EMailService emailService;

    /**
     * Finalizes an order and sends a notification email to the user associated with the order.
     *
     * <p>This method coordinates the following actions:
     * <ol>
     *     <li>Delegates the finalization of the order to the {@link OrderFacade}.</li>
     *     <li>Sends a notification email to the user via the {@link EMailService}, using the user ID
     *     retrieved from the finalized {@link Order}.</li>
     * </ol>
     *
     * @param totalPrice the total price of the order to be finalized
     * @return the finalized {@link Order} object
     * @throws IllegalArgumentException if the totalPrice is null or less than zero
     */
    public Order finalizeOrder(BigDecimal totalPrice) {
        Order order = orderFacade.finalizeOrder(totalPrice);
        emailService.sendEmail(order.getUserId());
        return order;
    }
}
