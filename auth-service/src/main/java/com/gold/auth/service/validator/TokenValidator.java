package com.gold.auth.service.validator;

import com.gold.auth.service.TokenProvider;
import com.gold.core.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private final TokenProvider tokenProvider;

    public void validate(String token) {
        if (!tokenProvider.validateToken(token)) {
            throw new InvalidTokenException();
        }
    }
}