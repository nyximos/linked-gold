package com.gold.resource.controller.api;

import com.gold.core.wrapper.ResultResponse;
import com.gold.resource.controller.model.request.SignUpRequestModel;
import com.gold.resource.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
