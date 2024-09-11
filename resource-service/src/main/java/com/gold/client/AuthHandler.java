package com.gold.client;

import com.gold.core.exception.ErrorCode;
import com.gold.core.exception.GoldException;
import com.gold.core.exception.InvalidRefreshTokenException;
import com.gold.core.exception.InvalidTokenException;
import com.nyximos.auth.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final AuthClient authClient;

    public Auth.TokenResponse createToken(Long id, String username) {
        try {
            return authClient.createToken(id, username);
        }catch (Exception e) {
            throw new GoldException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.BAD_REQUEST);
        }
    }

    public Auth.ValidateTokenResponse validateToken(String accessToken) {
        try {
            return authClient.validateToken(accessToken);
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public Auth.TokenResponse reIssueToken(String refreshToken) {
        try {
            return authClient.reIssueToken(refreshToken);
        } catch (Exception e) {
            throw new InvalidRefreshTokenException();
        }
    }
}