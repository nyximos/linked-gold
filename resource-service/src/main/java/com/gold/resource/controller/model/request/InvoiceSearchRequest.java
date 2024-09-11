package com.gold.resource.controller.model.request;

import com.gold.core.code.InvoiceSearchType;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InvoiceSearchRequest {

    @Parameter(description = "검색일", example = "2024-09-11")
    private LocalDate date;

    @Parameter(description = "주문 유형", example = "PURCHASE")
    private InvoiceSearchType invoiceSearchType;

    @Parameter(description = "페이지 오프셋", example = "0")
    private int offset = 0;

    @Parameter(description = "페이지당 개수", example = "10")
    private int limit = 10;

}
