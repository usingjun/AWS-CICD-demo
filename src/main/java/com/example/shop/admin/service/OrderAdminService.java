package com.example.shop.admin.service;

import com.example.shop.admin.dao.OrderDeliveryRepository;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.global.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.shop.admin.service.OrderDeliveryUtil.orderDeliveryTemplate;

@Service
@RequiredArgsConstructor
public class OrderAdminService {

    private final EmailSender emailSender;
    private final OrderRepository orderRepository;
    private final OrderDeliveryRepository orderDeliveryRepository;

    @Async("customTaskExecutor")
    public void sendDeliveryAlertEmail(OrderDeliveryRequest order) {
        String subject = "[DevCourse Team 7] 주문하신 상품이 배송이 시작됐습니다.";
        String content = orderDeliveryTemplate(order);

        emailSender.sendMail(order.getEmail(), subject, content);
    }

    public void cachingDeliveryOrder(String email, Long orderId) {
        String key = "order:";
        if (LocalDateTime.now().getHour() < 14) {
            key += LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            key += LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        orderDeliveryRepository.addOrderEmail(key, new OrderDeliveryRequest(email, orderId));
    }
}
