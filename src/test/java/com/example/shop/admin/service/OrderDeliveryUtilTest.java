package com.example.shop.admin.service;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.shop.admin.service.OrderDeliveryUtil.orderDeliveryTemplate;
import static org.assertj.core.api.Assertions.assertThat;

class OrderDeliveryUtilTest {

    @DisplayName("Thymeleaf를 이용해 html 파일을 String으로 변환")
    @Test
    void orderDeliveryTemplateTest() {
        // given
        OrderDeliveryRequest order = new OrderDeliveryRequest("aaaaaaa@aaaa.com", "주문 번호 test");
        String orderDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));

        // when & then
        assertThat(orderDeliveryTemplate(order))
                .contains(order.getOrderNumber(), orderDate);
    }
}