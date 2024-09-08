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

    public String createToken(String username) {
        Auth.TokenRequest request = Auth.TokenRequest.newBuilder()
                .setUsername(username)
                .build();
        Auth.TokenResponse response = authServiceStub.createToken(request);
        return response.getAccessToken();
    }
}
