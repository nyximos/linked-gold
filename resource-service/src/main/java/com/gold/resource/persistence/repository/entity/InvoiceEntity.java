package com.gold.resource.persistence.repository.entity;

import com.gold.core.code.InvoiceType;
import com.gold.core.code.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "invoice")
public class InvoiceEntity extends BaseEntity {

    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_type", length = 20, nullable = false)
    private InvoiceType invoiceType;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unit_price;

    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 30, nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "gold_id", nullable = false)
    private Long goldId;
}
