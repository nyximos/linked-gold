package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class RefundedOrderException extends GoldException {
    public RefundedOrderException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.ORDER_REFUNDED);
    }

    public RefundedOrderException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public RefundedOrderException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}
