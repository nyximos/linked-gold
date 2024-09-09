package com.gold.resource.persistence.repository.entity;

import com.gold.core.code.GoldType;
import com.gold.core.code.InvoiceType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gold")
public class GoldEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "gold_type", length = 10, nullable = false)
    private GoldType goldType;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unit_price;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_type", length = 20, nullable = false)
    private InvoiceType invoiceType;

}
