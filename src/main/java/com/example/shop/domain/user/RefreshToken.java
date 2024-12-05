package com.example.shop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@Getter
@Builder
@AllArgsConstructor
@RedisHash(value="refreshToken",timeToLive = 60*60*24*7)
public class RefreshToken {
    @Id
    private Long memberId;
    private String refreshToken;
}
