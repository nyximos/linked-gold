package com.gold.core.handler;

import com.gold.core.exception.GoldException;
import com.gold.core.wrapper.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GoldException.class)
    public ResultResponse<Void> handleWantedException(GoldException ex) {
        return new ResultResponse<>(false, ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(x -> sb.append(x).append("\n"));
        return new ResultResponse<>(false, HttpStatus.BAD_REQUEST, sb.toString().trim());
    }
}