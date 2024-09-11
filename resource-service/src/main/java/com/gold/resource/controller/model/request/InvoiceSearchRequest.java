package com.gold.resource.controller.model.request;

import com.gold.core.code.InvoiceSearchType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InvoiceSearchRequest {
    private LocalDate date;
    private InvoiceSearchType invoiceSearchType;
    private int offset;
    private int limit;
}
