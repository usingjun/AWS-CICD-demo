package com.example.shop.auth.dto;

import com.example.shop.domain.user.Role;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserStatus;
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

    public static User toEntity(String email, String password, String name, Role role) {
        return User.builder()
                .email(email)
                .password(password)
                .userName(name)
                .userRole(role)
                .userStatus(UserStatus.ACTIVE)
                .build();
    }
}
