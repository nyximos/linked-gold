package com.gold.resource.service.domain;

import com.gold.core.code.GoldType;
import com.gold.core.code.InvoiceType;
import com.gold.core.code.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Gold {
    private Long id;
    private GoldType goldType;
    private BigDecimal unitPrice;
    private InvoiceType invoiceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BigDecimal calculateAmount(BigDecimal unit) {
        return unit.multiply(this.unitPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public OrderStatus determineOrderStatus() {
        if (this.invoiceType == InvoiceType.PURCHASE) {
            return OrderStatus.ORDER_COMPLETE;
        } else if (this.invoiceType == InvoiceType.SALE) {
            return OrderStatus.SALE_ORDER_COMPLETE;
        }
        throw new IllegalArgumentException("Unknown InvoiceType: " + this.invoiceType);
    }
}
