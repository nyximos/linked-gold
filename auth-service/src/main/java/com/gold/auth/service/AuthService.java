package com.gold.auth.service;

import com.nyximos.auth.Auth;
import com.nyximos.auth.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@GrpcService
@RequiredArgsConstructor
public class AuthService extends AuthServiceGrpc.AuthServiceImplBase {

    private final TokenProvider tokenProvider;

    @Override
    public void createToken(Auth.TokenRequest request, StreamObserver<Auth.TokenResponse> responseObserver) {
        String username = request.getUsername();

        Map<String, Object> tokenInfo = new HashMap<>();
        tokenInfo.put("username", username);
        String token = tokenProvider.issueToken(tokenInfo);

        Auth.TokenResponse tokenResponse = Auth.TokenResponse.newBuilder()
                .setAccessToken(token)
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