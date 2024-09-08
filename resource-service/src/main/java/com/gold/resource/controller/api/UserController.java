package com.gold.resource.controller.api;

import com.gold.client.AuthClient;
import com.gold.core.wrapper.ResultResponse;
import com.gold.resource.controller.model.request.LoginRequestModel;
import com.gold.resource.controller.model.request.SignUpRequestModel;
import com.gold.resource.controller.model.response.TokenModel;
import com.gold.resource.service.UserService;
import com.nyximos.auth.Auth;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.gold.core.constant.ResourceConstants.HEADER_AUTHORIZE_TOKEN;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/users")
public class UserController {

    private final UserService userService;
    private final AuthClient authClient; // Todo 낸주 삭제

    @PostMapping
    public ResultResponse<Void> signUp(@Valid @RequestBody SignUpRequestModel signUpRequestModel) {
        userService.signUp(signUpRequestModel);
        return new ResultResponse<>();
    }

    @PostMapping("/login")
    public ResultResponse<TokenModel> signIn(@Valid @RequestBody LoginRequestModel loginRequestModel) {
        return new ResultResponse(userService.signIn(loginRequestModel));
    }

    @GetMapping("/test")
    public ResultResponse<Void> test(@RequestHeader(value = HEADER_AUTHORIZE_TOKEN) String accessToken) {
        Auth.ValidateTokenResponse response = authClient.validateToken(accessToken);
        if (response.getIsValid()) {
            log.info(response.getUsername());
        }
        return new ResultResponse<>();
    }


}
