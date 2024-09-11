package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class AlreadyShippedOrderException extends GoldException {
    public AlreadyShippedOrderException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.ALREADY_SHIPPED_ORDER);
    }

    public AlreadyShippedOrderException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public AlreadyShippedOrderException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}