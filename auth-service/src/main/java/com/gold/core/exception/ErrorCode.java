package com.gold.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_TOKEN("유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN("유효하지 않은 리프레시 토큰입니다.")
    ;

    private String defaultMessage;

}
