package com.example.shop.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class BlackListRepository {

    private final StringRedisTemplate redisTemplate;
    private final String BLACK_LIST_VALUE="blackList";

    public void save(String key, Long expiration) {
        redisTemplate.opsForValue().set(key,BLACK_LIST_VALUE,expiration, TimeUnit.MILLISECONDS);
    }
    public Optional<String> findByToken(String token) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(token));
    }
}
