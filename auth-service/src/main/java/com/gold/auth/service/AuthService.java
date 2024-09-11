package com.gold.auth.service;

import com.gold.auth.converter.RefreshTokenConverter;
import com.gold.auth.persistence.repository.RefreshTokenRepository;
import com.gold.auth.service.validator.RefreshTokenValidator;
import com.gold.auth.service.validator.TokenValidator;
import com.gold.core.exception.GoldException;
import com.nyximos.auth.Auth;
import com.nyximos.auth.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@GrpcService
@RequiredArgsConstructor
public class AuthService extends AuthServiceGrpc.AuthServiceImplBase {

    @Value("${jwt.access.expire}")
    private long accessExpireDuration;

    @Value("${jwt.refresh.expire}")
    private long refreshExpireDuration;

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenConverter refreshTokenConverter;
    private final TokenValidator tokenValidator;
    private final RefreshTokenValidator refreshTokenValidator;

    @Override
    @Transactional
    public void createToken(Auth.TokenRequest request, StreamObserver<Auth.TokenResponse> responseObserver) {
        long userId = request.getId();
        String username = request.getUsername();
        long current = System.currentTimeMillis();

        Map<String, Object> tokenInfo = createTokenInfo(userId, username);
        String accessToken = tokenProvider.issueToken(tokenInfo, current, accessExpireDuration);
        String refreshToken = tokenProvider.issueToken(tokenInfo, current, refreshExpireDuration);
        refreshTokenRepository.save(refreshTokenConverter.convert(userId, tokenProvider.removePrefix(refreshToken), current+refreshExpireDuration));

        Auth.TokenResponse tokenResponse = createTokenResponse(accessToken, refreshToken);

        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void validateToken(Auth.ValidateTokenRequest request, StreamObserver<Auth.ValidateTokenResponse> responseObserver) {
        try {
            String accessToken = tokenProvider.removePrefix(request.getAccessToken());
            tokenProvider.validateToken(accessToken);

            Auth.ValidateTokenResponse response = Auth.ValidateTokenResponse.newBuilder()
                    .setId(tokenProvider.extractId(accessToken))
                    .setUsername(tokenProvider.extractUsername(accessToken))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (GoldException e) {
            responseObserver.onError(new RuntimeException(e.getMessage()));
        }
    }

    @Override
    @Transactional
    public void reIssueToken(Auth.ReIssueTokenRequest request, StreamObserver<Auth.TokenResponse> responseObserver) {
        try {
            String refreshToken = tokenProvider.removePrefix(request.getRefreshToken());
            String username = tokenProvider.extractUsername(refreshToken);
            Long userId = tokenProvider.extractId(refreshToken);

            tokenValidator.validate(refreshToken);
            refreshTokenValidator.validate(refreshToken, userId);

            Map<String, Object> tokenInfo = createTokenInfo(userId, username);
            long current = System.currentTimeMillis();
            String accessToken = tokenProvider.issueToken(tokenInfo, current, accessExpireDuration);
            String newRefreshToken = tokenProvider.issueToken(tokenInfo, current, refreshExpireDuration);
            refreshTokenRepository.save(refreshTokenConverter.convert(userId, tokenProvider.removePrefix(newRefreshToken), current + refreshExpireDuration));
            Auth.TokenResponse tokenResponse = createTokenResponse(accessToken, newRefreshToken);

            responseObserver.onNext(tokenResponse);
            responseObserver.onCompleted();
        } catch (GoldException e) {
            responseObserver.onError(new RuntimeException(e.getMessage()));
        }
    }

    private Map<String, Object> createTokenInfo(Long id, String username) {
        Map<String, Object> tokenInfo = new HashMap<>();
        tokenInfo.put("id", id);
        tokenInfo.put("username", username);
        return tokenInfo;
    }

    private Auth.TokenResponse createTokenResponse(String accessToken, String refreshToken) {
        return Auth.TokenResponse.newBuilder()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .build();
    }

}