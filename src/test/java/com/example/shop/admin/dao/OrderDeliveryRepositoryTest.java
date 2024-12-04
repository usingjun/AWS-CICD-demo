package com.example.shop.admin.dao;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderDeliveryRepositoryTest {

    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    private final String key = "orders:" + LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);

    @BeforeEach
    void setup() {
        for (Long i = 0L; i < 150; i++) {
            OrderDeliveryRequest order = new OrderDeliveryRequest("test%s@test.com".formatted(i), i*102);

            orderDeliveryRepository.addOrderEmail(key, order);
        }
    }

    @AfterEach
    void tearDown() {
        orderDeliveryRepository.asyncDelete(key);
    }

    @DisplayName("addOrderEmail 메서드 테스트")
    @Test
    void addOrderEmail() {
        // given
        OrderDeliveryRequest request = new OrderDeliveryRequest("testtest@test.com", 999999L);
        // when
        Long addCount = orderDeliveryRepository.addOrderEmail(key, request);

        // then
        assertThat(addCount).isEqualTo(1);
    }

    @DisplayName("popOrder 메서드 테스트")
    @Test
    void popOrder() {
        // given
        Set<OrderDeliveryRequest> emails = orderDeliveryRepository.findAllOrder(key);

        // when
        OrderDeliveryRequest  actualData = orderDeliveryRepository.popOrder(key);

        // then
        assertThat(emails).doesNotContain(actualData);
    }

    @DisplayName("removeOrderEmail 메서드 테스트")
    @Test
    void removeOrderEmail() {
        // when
        Long actualData = orderDeliveryRepository.removeOrderEmail(key,
                orderDeliveryRepository.findAllOrder(key).toArray());

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualData).isEqualTo(150);
            softAssertions.assertThat(orderDeliveryRepository.findAllOrder(key)).isEmpty();
        });
    }

    @DisplayName("asyncDelete 메서드 테스트")
    @Test
    void asyncDelete() {
        // when
        orderDeliveryRepository.asyncDelete(key);

        // then
        assertThat(orderDeliveryRepository.findAllOrder(key)).isEmpty();
    }
}