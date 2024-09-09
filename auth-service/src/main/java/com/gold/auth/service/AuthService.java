package com.gold.auth.service;

import com.gold.auth.converter.RefreshTokenConverter;
import com.gold.auth.persistence.repository.RefreshTokenRepository;
import com.nyximos.auth.Auth;
import com.nyximos.auth.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Transactional
    @Override
    public void createToken(Auth.TokenRequest request, StreamObserver<Auth.TokenResponse> responseObserver) {
        String username = request.getUsername();

        Map<String, Object> tokenInfo = new HashMap<>();
        tokenInfo.put("username", username);

        long current = System.currentTimeMillis();

        String accessToken = tokenProvider.issueToken(tokenInfo, current, accessExpireDuration);
        String refreshToken = tokenProvider.issueToken(tokenInfo, current, refreshExpireDuration);

        refreshTokenRepository.save(refreshTokenConverter.convert(username, tokenProvider.removePrefix(refreshToken), current+refreshExpireDuration));

        Auth.TokenResponse tokenResponse = Auth.TokenResponse.newBuilder()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .build();

        responseObserver.onNext(tokenResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void validateToken(Auth.ValidateTokenRequest request, StreamObserver<Auth.ValidateTokenResponse> responseObserver) {
        String accessToken = tokenProvider.removePrefix(request.getAccessToken());

        boolean isValid = tokenProvider.validateToken(accessToken);

        Auth.ValidateTokenResponse response = Auth.ValidateTokenResponse.newBuilder()
                .setIsValid(isValid)
                .setUsername(isValid ? tokenProvider.extractUsername(accessToken) : StringUtils.EMPTY)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}