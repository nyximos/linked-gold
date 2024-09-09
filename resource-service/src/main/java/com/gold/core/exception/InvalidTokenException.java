package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends GoldException {
    public InvalidTokenException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN);
    }

    public InvalidTokenException(ErrorCode errorCode) {
        super(HttpStatus.UNAUTHORIZED, errorCode);
    }

    public InvalidTokenException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}