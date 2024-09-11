package com.gold.resource.service.delegator.validate;

import com.gold.core.code.OrderStatus;
import com.gold.core.exception.PaymentFailedException;
import com.gold.resource.persistence.repository.entity.InvoiceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentValidator {

    public void validate(InvoiceEntity invoice) {
        if (!OrderStatus.ORDER_COMPLETE.equals(invoice.getOrderStatus())) {
            throw new PaymentFailedException();
        }
    }
}
