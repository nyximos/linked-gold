package com.gold.resource.controller.api;

import com.gold.core.constant.ResourceConstants;
import com.gold.core.wrapper.ResultResponse;
import com.gold.resource.controller.model.response.TokenModel;
import com.gold.resource.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/tokens")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/reissue")
    public ResultResponse<TokenModel> reIssue(@RequestHeader(ResourceConstants.HEADER_AUTHORIZE_TOKEN) String refreshToken) {
        return new ResultResponse<>(tokenService.reIssue(refreshToken));
    }

}
