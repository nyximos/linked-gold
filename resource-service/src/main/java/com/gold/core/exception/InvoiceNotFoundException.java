package com.gold.core.exception;

import org.springframework.http.HttpStatus;

public class InvoiceNotFoundException extends GoldException {
    public InvoiceNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.INVOICE_NOT_FOUND_EXCEPTION);
    }

    public InvoiceNotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }

    public InvoiceNotFoundException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(httpStatus, errorCode);
    }

}