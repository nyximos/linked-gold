package com.gold.client;

import com.nyximos.auth.Auth;
import com.nyximos.auth.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class AuthClient {

    private final AuthServiceGrpc.AuthServiceBlockingStub authServiceStub;

    public AuthClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        authServiceStub = AuthServiceGrpc.newBlockingStub(channel);
    }

    public Auth.TokenResponse createToken(Long id, String username) {
        Auth.TokenRequest request = Auth.TokenRequest.newBuilder()
                .setId(id)
                .setUsername(username)
                .build();
        Auth.TokenResponse response = authServiceStub.createToken(request);
        return response;
    }

    public Auth.ValidateTokenResponse validateToken(String accessToken) {
        Auth.ValidateTokenRequest request = Auth.ValidateTokenRequest.newBuilder()
                .setAccessToken(accessToken)
                .build();
        Auth.ValidateTokenResponse response = authServiceStub.validateToken(request);
        return response;
    }

    public Auth.TokenResponse reIssueToken(String refreshToken) {
        Auth.ReIssueTokenRequest request = Auth.ReIssueTokenRequest.newBuilder()
                .setRefreshToken(refreshToken)
                .build();
        return authServiceStub.reIssueToken(request);
    }
}
