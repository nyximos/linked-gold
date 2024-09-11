package com.gold.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceSearchType {

    PURCHASE("구매"),
    SALE("판매"),
    ALL
            ("전부")
    ;

    private final String description;
}
