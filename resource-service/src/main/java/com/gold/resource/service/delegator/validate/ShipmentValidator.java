package com.gold.resource.service.delegator.validate;

import com.gold.core.code.InvoiceType;
import com.gold.core.code.OrderStatus;
import com.gold.core.exception.AlreadyShippedOrderException;
import com.gold.core.exception.OrderCanceledException;
import com.gold.core.exception.RefundedOrderException;
import com.gold.core.exception.UnpaidOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentValidator {

    public void validate(OrderStatus orderStatus) {
        if (OrderStatus.ORDER_COMPLETE.equals(orderStatus)) {
            throw new UnpaidOrderException();
        }
        if (OrderStatus.ORDER_CANCEL.equals(orderStatus)) {
            throw new OrderCanceledException();
        }
        if (OrderStatus.REFUNDED.equals(orderStatus)) {
            throw new RefundedOrderException();
        }
        if (OrderStatus.SHIPMENT_COMPLETE.equals(orderStatus)) {
            throw new AlreadyShippedOrderException();
        }
    }
}