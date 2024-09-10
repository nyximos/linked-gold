package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class GoldNotFoundException extends GoldException {
    public GoldNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.GOLD_NOT_FOUND_EXCEPTION);
    }

    public GoldNotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }

    public GoldNotFoundException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }

}