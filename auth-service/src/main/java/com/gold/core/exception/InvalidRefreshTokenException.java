package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends GoldException {
    public InvalidRefreshTokenException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_REFRESH_TOKEN);
    }

    public InvalidRefreshTokenException(ErrorCode errorCode) {
        super(HttpStatus.UNAUTHORIZED, errorCode);
    }

    public InvalidRefreshTokenException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }
}