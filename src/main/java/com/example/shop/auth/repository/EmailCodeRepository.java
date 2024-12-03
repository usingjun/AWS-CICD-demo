package com.example.shop.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailCodeRepository {

    private final StringRedisTemplate redisTemplate;

    public Optional<String> findAuthCode(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public void save(String key, String value, long duration) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);

        valueOperations.set(key, value, expireDuration);
    }

    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }
}
