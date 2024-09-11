package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class UnpaidOrderException extends GoldException {
    public UnpaidOrderException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.UNPAID_ORDER);
    }

    public UnpaidOrderException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public UnpaidOrderException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}