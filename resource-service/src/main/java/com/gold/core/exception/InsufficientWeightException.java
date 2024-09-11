package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class InsufficientWeightException extends GoldException {
    public InsufficientWeightException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.INSUFFICIENT_WEIGHT_EXCEPTION);
    }

    public InsufficientWeightException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public InsufficientWeightException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}
