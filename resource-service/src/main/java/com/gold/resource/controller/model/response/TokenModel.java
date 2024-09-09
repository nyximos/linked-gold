package com.gold.resource.controller.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenModel {
    private String accessToken;
    private String refreshToken;
}
