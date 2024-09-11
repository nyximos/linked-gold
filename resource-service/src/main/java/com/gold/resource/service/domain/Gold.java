package com.gold.resource.service.domain;

import com.gold.core.code.GoldType;
import com.gold.core.code.InvoiceType;
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
    private BigDecimal weight;
    private InvoiceType invoiceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BigDecimal calculateAmount(BigDecimal weight) {
        return weight.multiply(this.unitPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
