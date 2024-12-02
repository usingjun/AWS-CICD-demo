package com.example.shop.auth.dto;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String email;
    private String password;
}
