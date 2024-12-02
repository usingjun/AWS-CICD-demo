package com.example.shop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String refreshToken;
    private String memberId;

}
