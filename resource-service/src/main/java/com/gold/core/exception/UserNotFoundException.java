package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GoldException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND_EXCEPTION);
    }

    public UserNotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }

    public UserNotFoundException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }

}