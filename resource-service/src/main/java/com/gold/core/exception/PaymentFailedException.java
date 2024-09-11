package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class PaymentFailedException extends GoldException {
    public PaymentFailedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.PAYMENT_FAILED);
    }

    public PaymentFailedException(ErrorCode errorCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
    }

    public PaymentFailedException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
        }

    }