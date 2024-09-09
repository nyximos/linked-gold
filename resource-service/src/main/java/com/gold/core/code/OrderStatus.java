package com.gold.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    ORDER_COMPLETE("구매 주문 완료"),
    ORDER_PAYMENT_COMPLETE("구매 입금 완료"),
    ORDER_SHIPMENT_COMPLETE("구매 발송 완료"),
    SALE_ORDER_COMPLETE("판매 주문 완료"),
    SALE_REMITTANCE_COMPLETE("판매 송금 완료"),
    REMITTANCE_RECEIPT_COMPLETE("판매 수령 완료")
    ;

    private final String description;

}
