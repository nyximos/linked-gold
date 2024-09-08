package com.gold.auth.service;

import com.nyximos.auth.Auth;
import com.nyximos.auth.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

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
}