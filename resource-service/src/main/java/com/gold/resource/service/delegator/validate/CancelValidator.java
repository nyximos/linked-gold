package com.gold.resource.service.delegator.validate;

import com.gold.core.code.OrderStatus;
import com.gold.core.exception.UnpaidOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelValidator {

    public void validate(OrderStatus orderStatus) {
        if (OrderStatus.ORDER_COMPLETE.equals(orderStatus)) {
            throw new UnpaidOrderException();
        }
    }
}
