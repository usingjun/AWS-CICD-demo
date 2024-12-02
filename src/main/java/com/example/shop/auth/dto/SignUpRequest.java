package com.example.shop.auth.dto;

import com.example.shop.domain.user.Role;
import com.example.shop.domain.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
    private String name;

    public static Users toEntity(String email, String password, String name, Role role) {
        return Users.builder()
                .email(email)
                .password(password)
                .userName(name)
                .userRole(role)
                .build();
    }
}
