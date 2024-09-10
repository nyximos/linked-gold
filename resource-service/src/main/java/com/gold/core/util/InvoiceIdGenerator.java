package com.gold.core.util;

import com.gold.core.code.InvoiceType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static com.gold.core.constant.ResourceConstants.HYPHEN;

public class InvoiceIdGenerator {

    public static String generateOrderId(InvoiceType invoiceType) {
        StringBuilder sb = new StringBuilder();
        sb.append(invoiceType.getInvoiceIdPrefix());
        sb.append(HYPHEN);
        sb.append(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        sb.append(HYPHEN);
        sb.append(UUID.randomUUID().toString().substring(0, 6));
        return sb.toString();
    }

}
