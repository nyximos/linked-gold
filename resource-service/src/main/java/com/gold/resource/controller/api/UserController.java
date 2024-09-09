package com.gold.resource.controller.api;

import com.gold.core.wrapper.ResultResponse;
import com.gold.resource.controller.model.request.LoginRequestModel;
import com.gold.resource.controller.model.request.SignUpRequestModel;
import com.gold.resource.controller.model.response.TokenModel;
import com.gold.resource.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResultResponse<Void> signUp(@Valid @RequestBody SignUpRequestModel signUpRequestModel) {
        userService.signUp(signUpRequestModel);
        return new ResultResponse<>();
    }

    @PostMapping("/login")
    public ResultResponse<TokenModel> signIn(@Valid @RequestBody LoginRequestModel loginRequestModel) {
        return new ResultResponse(userService.signIn(loginRequestModel));
    }

}
