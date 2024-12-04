package com.example.shop.admin.service;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.shop.admin.service.OrderDeliveryUtil.orderDeliveryTemplate;
import static org.assertj.core.api.Assertions.assertThat;

class OrderDeliveryUtilTest {

    @DisplayName("UnknownFormatConversionException: Conversion = ';' 오류 해결")
    @Test
    void orderDeliveryTemplateTest() {
        // given
        OrderDeliveryRequest order = new OrderDeliveryRequest("aaaaaaa@aaaa.com", 13L);

        // when & then
        assertThat(order.getEmail()).isEqualTo("aaaaaaa@aaaa.com");
        assertThat(order.getOrderId()).isEqualTo(13L);
        assertThat(orderDeliveryTemplate(order))
                .contains(order.getOrderId().toString());
    }
}