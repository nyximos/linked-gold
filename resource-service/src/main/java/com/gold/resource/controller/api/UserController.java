package com.gold.resource.controller.api;

import com.gold.core.wrapper.ResultResponse;
import com.gold.resource.controller.model.request.LoginRequestModel;
import com.gold.resource.controller.model.request.SignUpRequestModel;
import com.gold.resource.controller.model.response.TokenModel;
import com.gold.resource.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/users")
@Tag(name = "사용자 API", description = "사용자 회원가입 및 로그인 관련 작업을 처리합니다.")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "회원가입", description = "회원가입을 합니다..")
    public ResultResponse<Void> signUp(@Valid @RequestBody SignUpRequestModel signUpRequestModel) {
        userService.signUp(signUpRequestModel);
        return new ResultResponse<>();
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 후 토큰을 발급받습니다.")
    public ResultResponse<TokenModel> signIn(@Valid @RequestBody LoginRequestModel loginRequestModel) {
        return new ResultResponse(userService.signIn(loginRequestModel));
    }

}
