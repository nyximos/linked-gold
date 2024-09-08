package com.gold.resource.controller.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestModel {

    @NotNull(message = "email은 null이 될 수 없습니다.")
    @Email(message = "유효한 이메일 주소를 입력해 주세요.")
    private String email;

    @NotNull(message = "password는 null이 될 수 없습니다.")
    private String password;
}
