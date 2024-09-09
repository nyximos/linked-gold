package com.gold.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GoldException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public GoldException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.httpStatus = httpStatus;
        this.message = errorCode.getDefaultMessage();
    }

}