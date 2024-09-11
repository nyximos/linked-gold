package com.gold.resource.service;

import com.gold.core.code.InvoiceSearchType;
import com.gold.core.code.OrderStatus;
import com.gold.core.exception.InvoiceNotFoundException;
import com.gold.core.wrapper.PageResponse;
import com.gold.resource.controller.model.response.InvoiceListResponse;
import com.gold.resource.controller.model.response.InvoiceResponse;
import com.gold.resource.converter.InvoiceConverter;
import com.gold.resource.persistence.repository.InvoiceRepository;
import com.gold.resource.persistence.repository.entity.InvoiceEntity;
import com.gold.resource.service.delegator.validate.*;
import com.gold.resource.service.domain.Gold;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final UserValidator userValidator;
    private final GoldService goldService;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceConverter invoiceConverter;
    private final GoldWeightValidator goldWeightValidator;
    private final PaymentValidator paymentValidator;
    private final ShipmentValidator shipmentValidator;
    private final CancelValidator cancelValidator;

    @Transactional
    public void createInvoice(Long userId, Long goldId, BigDecimal weight) {
        userValidator.validate(userId);
        Gold gold = goldService.getGold(goldId);
        goldWeightValidator.validateWeight(gold.getWeight(), weight);
        gold = goldService.subtractWeight(goldId, weight);
        invoiceRepository.save(invoiceConverter.convert(userId, gold, weight));
    }

    @Transactional(readOnly = true)
    public InvoiceResponse getInvoice(String invoiceId, Long userId, String customerEmail) {
        InvoiceEntity invoice = invoiceRepository.findByIdAndCustomerId(invoiceId, userId).orElseThrow(InvoiceNotFoundException::new);
        Gold gold = goldService.getGold(invoice.getGoldId());
        return invoiceConverter.convertToInvoiceResponse(invoice, gold, customerEmail);
    }

    @Transactional
    public void payment(String invoiceId) {
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException());
        paymentValidator.validate(invoice.getOrderStatus());
        invoice.updateOrderStatus(OrderStatus.PAYMENT_COMPLETE);
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void shipment(String invoiceId) {
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException());
        shipmentValidator.validate(invoice.getOrderStatus());
        invoice.updateOrderStatus(OrderStatus.SHIPMENT_COMPLETE);
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void cancel(String invoiceId) {
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException());
        cancelValidator.validate(invoice.getOrderStatus());
        invoice.updateOrderStatus(OrderStatus.ORDER_CANCEL);
        goldService.addWeight(invoice.getGoldId(), invoice.getWeight());
        invoiceRepository.save(invoice);
    }

    @Transactional(readOnly = true)
    public PageResponse<InvoiceListResponse> getInvoices(Long userId, LocalDate date, InvoiceSearchType invoiceSearchType, int offset, int limit) {
        PageResponse<InvoiceEntity> invoices = invoiceRepository.findInvoices(userId, date, invoiceSearchType, offset, limit);
        return new PageResponse(invoices.getTotal(), invoiceConverter.convert(invoices.getContents()));
    }
}
