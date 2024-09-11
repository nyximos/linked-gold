package com.gold.resource.controller.model.response;

import com.gold.core.code.InvoiceType;
import com.gold.core.code.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceListResponse {
    private String id;
    private InvoiceType invoiceType;
    private BigDecimal amount;
    private BigDecimal unitPrice;
    private BigDecimal weight;
    private OrderStatus orderStatus;
    private Long goldId;
    private String goldType;
    private LocalDateTime createdAt;
}
