package com.gold.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.gold.core.constant.ResourceConstants.PERCHASE_PREFIX;
import static com.gold.core.constant.ResourceConstants.SALE_PREFIX;

@Getter
@AllArgsConstructor
public enum InvoiceType {

    PURCHASE("구매"),
    SALE("판매");

    private String description;

    public String getInvoiceIdPrefix() {
        if (InvoiceType.PURCHASE.equals(this)) {
            return PERCHASE_PREFIX;
        } else {
            return SALE_PREFIX;
        }
    }
}
