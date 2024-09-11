package com.gold.resource.converter;

import com.gold.resource.persistence.repository.entity.InvoiceEntity;
import com.gold.resource.service.domain.Gold;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface InvoiceConverter {

    @Mapping(target = "id", expression = "java(com.gold.core.util.InvoiceIdGenerator.generateOrderId(gold.getInvoiceType()))")
    @Mapping(target = "invoiceType", source = "gold.invoiceType")
    @Mapping(target = "goldId", source = "gold.id")
    @Mapping(target = "customerId", source = "userId")
    @Mapping(target = "unit_price", source = "gold.unitPrice")
    @Mapping(target = "amount", expression = "java(gold.calculateAmount(unit))")
    @Mapping(target = "orderStatus", expression = "java(gold.determineOrderStatus())")
    InvoiceEntity convert(Long userId, Gold gold, BigDecimal unit);

}
