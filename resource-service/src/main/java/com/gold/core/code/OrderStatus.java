package com.gold.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    ORDER_COMPLETE("주문 완료"),
    PAYMENT_COMPLETE("결제 완료"),
    SHIPMENT_COMPLETE("발송 완료"),
    ORDER_CANCEL("주문 취소"),
    REFUNDED("환불 완료")
    ;

    private final String description;

}
