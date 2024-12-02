package com.example.shop.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {
    private String accessToken;
    private String grantType;

    public static SignInResponse of(String accessToken, String grantType) {
        return SignInResponse.builder()
                .accessToken(accessToken)
                .grantType(grantType)
                .build();
    }
}
