package com.gold.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND("해당 자원을 찾을 수 없습니다."),
    BAD_REQUEST("잘못된 요청입니다."),
    EMAIL_ALREADY_IN_USE("이미 사용중인 이메일입니다."),
    USER_NOT_FOUND_EXCEPTION("유저를 찾을 수 없습니다.")
    ;

    private String defaultMessage;

}