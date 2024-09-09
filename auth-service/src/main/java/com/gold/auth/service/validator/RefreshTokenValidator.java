package com.gold.auth.service.validator;

import com.gold.auth.persistence.repository.RefreshTokenRepository;
import com.gold.core.exception.InvalidRefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenValidator {

    private final RefreshTokenRepository refreshTokenRepository;

    public void validate(String refreshToken, String username) {
        refreshTokenRepository.findById(username)
                .filter(entity -> entity.getRefreshToken().equals(refreshToken))
                .orElseThrow(InvalidRefreshTokenException::new);
    }
}