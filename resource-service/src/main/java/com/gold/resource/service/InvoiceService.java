package com.gold.resource.service;

import com.gold.resource.converter.InvoiceConverter;
import com.gold.resource.persistence.repository.InvoiceRepository;
import com.gold.resource.service.delegator.validate.UserValidator;
import com.gold.resource.service.domain.Gold;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final UserValidator userValidator;
    private final GoldService goldService;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceConverter invoiceConverter;

    @Transactional
    public void createInvoice(Long userId, Long goldId, BigDecimal unit) {
        userValidator.validate(userId);
        Gold gold = goldService.getGold(goldId);
        invoiceRepository.save(invoiceConverter.convert(userId, gold, unit));
    }
}
