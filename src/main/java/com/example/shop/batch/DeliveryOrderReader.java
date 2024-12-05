package com.example.shop.batch;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKey;

@Component
@RequiredArgsConstructor
public class DeliveryOrderReader implements ItemStreamReader<OrderDeliveryRequest> {

    private final RedisTemplate<String, OrderDeliveryRequest> redisTemplate;
    private Cursor<OrderDeliveryRequest> cursor;

    @Override
    public void open(ExecutionContext executionContext) {
        cursor = redisTemplate.opsForSet().scan(getOrderKey(), ScanOptions.NONE);
    }

    @Override
    public OrderDeliveryRequest read() throws Exception {
        if (cursor != null && cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }
}
