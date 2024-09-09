package com.gold.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceType {

    PURCHASE("구매"),
    SALE("판매");

    private String description;
}
