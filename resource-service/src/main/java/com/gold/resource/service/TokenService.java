package com.gold.resource.service;

import com.gold.client.AuthClient;
import com.gold.resource.controller.model.response.TokenModel;
import com.gold.resource.converter.TokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthClient authClient;
    private final TokenConverter tokenConverter;

    public TokenModel reIssue(String refreshToken) {
        return tokenConverter.convert(authClient.reIssueToken(refreshToken));
    }
}
