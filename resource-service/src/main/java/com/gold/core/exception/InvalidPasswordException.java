package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends GoldException {
    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_PASSWORD);
    }

    public InvalidPasswordException(ErrorCode errorCode) {
        super(HttpStatus.UNAUTHORIZED, errorCode);
    }

    public InvalidPasswordException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}

