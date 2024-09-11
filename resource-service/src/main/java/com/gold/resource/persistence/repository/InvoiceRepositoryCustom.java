package com.gold.resource.persistence.repository;

import com.gold.core.code.InvoiceSearchType;
import com.gold.core.wrapper.PageResponse;
import com.gold.resource.persistence.repository.entity.InvoiceEntity;

import java.time.LocalDate;

public interface InvoiceRepositoryCustom {
    PageResponse<InvoiceEntity> findInvoices(Long userId, LocalDate date, InvoiceSearchType invoiceType, int offset, int limit);
}
