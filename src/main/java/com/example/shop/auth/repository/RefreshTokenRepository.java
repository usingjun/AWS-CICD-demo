package com.example.shop.auth.repository;

import com.example.shop.domain.user.RefreshToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RefreshTokenRepository {
    private final RedisTemplate redisTemplate;
    public RefreshTokenRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private final int EXPIRE_TIME=7;

    // <key : tokenValue, value : memberId> 형태로 저장
    public void save(final RefreshToken refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getMemberId(), refreshToken.getRefreshToken());
        redisTemplate.expire(refreshToken.getRefreshToken(),EXPIRE_TIME, TimeUnit.DAYS);
    }

    public Optional<RefreshToken> findByToken(String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String memberId = valueOperations.get(refreshToken);
        if(Objects.isNull(memberId)) return Optional.empty();
        return Optional.of(new RefreshToken(refreshToken, memberId));
    }

    public void deleteByRefreshToken(String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        //key:token 삭제
        String id = valueOperations.getAndDelete(refreshToken);
        //key:id 삭제
        if(Objects.isNull(id)) valueOperations.getAndDelete(id);
    }

}
