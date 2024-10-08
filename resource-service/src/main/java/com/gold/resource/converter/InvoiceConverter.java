package com.gold.resource.converter;

import com.gold.resource.controller.model.response.InvoiceListResponse;
import com.gold.resource.controller.model.response.InvoiceResponse;
import com.gold.resource.persistence.repository.entity.InvoiceEntity;
import com.gold.resource.service.domain.Gold;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceConverter {

    @Mapping(target = "id", expression = "java(com.gold.core.util.InvoiceIdGenerator.generateOrderId(gold.getInvoiceType()))")
    @Mapping(target = "invoiceType", source = "gold.invoiceType")
    @Mapping(target = "goldId", source = "gold.id")
    @Mapping(target = "customerId", source = "userId")
    @Mapping(target = "unitPrice", source = "gold.unitPrice")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "amount", expression = "java(gold.calculateAmount(weight))")
    @Mapping(target = "orderStatus", constant = "ORDER_COMPLETE")
    InvoiceEntity convert(Long userId, Gold gold, BigDecimal weight);

    @Mapping(target = "id", source = "invoice.id")
    @Mapping(target = "invoiceType", source = "invoice.invoiceType")
    @Mapping(target = "amount", source = "invoice.amount")
    @Mapping(target = "unitPrice", source = "invoice.unitPrice")
    @Mapping(target = "weight", source = "invoice.weight")
    @Mapping(target = "orderStatus", source = "invoice.orderStatus")
    @Mapping(target = "goldId", source = "invoice.goldId")
    @Mapping(target = "goldType", expression = "java(gold.getGoldType().getDescription())")
    @Mapping(target = "createdAt", source = "invoice.createdAt")
    @Mapping(target = "updatedAt", source = "invoice.updatedAt")
    InvoiceResponse convert(InvoiceEntity invoice, Gold gold, String customerEmail);

    @Mapping(target = "goldType", expression = "java(source.getInvoiceType().name())")
    InvoiceListResponse convert(InvoiceEntity source);

    List<InvoiceListResponse> convert(List<InvoiceEntity> source);
}
