package com.gold.resource.service.delegator.validate;

import com.gold.core.code.OrderStatus;
import com.gold.core.exception.PaymentFailedException;
import org.springframework.stereotype.Service;

@Service
public class PaymentValidator {

    public void validate(OrderStatus orderStatus) {
        if (!OrderStatus.ORDER_COMPLETE.equals(orderStatus)) {
            throw new PaymentFailedException();
        }
    }
}
