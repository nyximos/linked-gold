package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class OrderCanceledException extends GoldException {
    public OrderCanceledException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.ORDER_CANCELED);
    }

    public OrderCanceledException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public OrderCanceledException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}
