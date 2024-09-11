package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class UnknownInvoiceTypeException extends GoldException {
    public UnknownInvoiceTypeException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.UNKNOWN_INVOICE_TYPE);
    }

    public UnknownInvoiceTypeException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }

    public UnknownInvoiceTypeException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }

}