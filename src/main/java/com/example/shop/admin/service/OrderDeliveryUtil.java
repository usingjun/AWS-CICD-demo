package com.example.shop.admin.service;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.shop.global.util.TemplateEnginUtil.templateEngine;

public final class OrderDeliveryUtil {

    private static final DateTimeFormatter KOREAN_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    static String orderDeliveryTemplate(OrderDeliveryRequest order) {
        String orderNumber = order.getOrderNumber();
        String deliveryDate = LocalDate.now().format(KOREAN_DATE_FORMATTER);

        Context context = new Context();
        context.setVariable("orderNumber", orderNumber);
        context.setVariable("deliveryDate", deliveryDate);
        return templateEngine.process("delivery-email", context);
    }
}
