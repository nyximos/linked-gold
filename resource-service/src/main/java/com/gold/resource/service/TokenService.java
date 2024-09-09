package com.gold.resource.service;

import com.gold.client.AuthHandler;
import com.gold.resource.controller.model.response.TokenModel;
import com.gold.resource.converter.TokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthHandler authHandler;
    private final TokenConverter tokenConverter;

    public TokenModel issue(Long id, String email) {
        return tokenConverter.convert(authHandler.createToken(id, email));
    }

    public TokenModel reIssue(String refreshToken) {
        return tokenConverter.convert(authHandler.reIssueToken(refreshToken));
    }
}
