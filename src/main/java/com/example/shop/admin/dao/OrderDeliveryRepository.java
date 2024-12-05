package com.example.shop.admin.dao;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class OrderDeliveryRepository {

    private final RedisTemplate<String, OrderDeliveryRequest> redisTemplate;

    public Long addOrderEmail(String key, OrderDeliveryRequest value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    public OrderDeliveryRequest popOrder(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    public Long removeOrderEmail(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    public Set<OrderDeliveryRequest> findAllOrder(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void asyncDelete(String key) {
        redisTemplate.execute((RedisConnection connection) -> connection.unlink(key.getBytes(StandardCharsets.UTF_8)));
    }
}
