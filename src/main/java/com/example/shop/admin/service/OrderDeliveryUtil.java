package com.example.shop.admin.service;

import com.example.shop.admin.dto.OrderDeliveryRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class OrderDeliveryUtil {

    private static final DateTimeFormatter KOREAN_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    static String orderDeliveryTemplate(OrderDeliveryRequest order) {
        StringBuilder sb = new StringBuilder();

        String orderId = Objects.requireNonNull(order.getOrderId().toString());
        String deliveryDate = LocalDate.now().format(KOREAN_DATE_FORMATTER);

        sb.append("<!DOCTYPE html>")
                .append("<html lang=\"ko\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                .append("<title>배송 완료 알림</title>")
                .append("</head>")
                .append("<body style=\"font-family: 'Arial', sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;\">")
                .append("<table role=\"presentation\" style=\"width: 100%; background-color: #f4f4f4; padding: 20px;\">")
                .append("<tr>")
                .append("<td style=\"text-align: center; padding: 10px;\">")
                .append("<h1 style=\"color: #2c3e50;\">배송이 시작되었습니다!</h1>")
                .append("<p style=\"font-size: 16px; color: #34495e;\">고객님의 주문이 배송 시작되었습니다. 감사합니다!</p>")
                .append("</td>")
                .append("</tr>")
                .append("<tr>")
                .append("<td style=\"background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);\">")
                .append("<h2 style=\"color: #2980b9;\">주문 정보</h2>")
                .append("<table style=\"width: 100%; border-collapse: collapse;\">")
                .append("<tr>")
                .append("<td style=\"padding: 10px; border-bottom: 1px solid #ddd; color: #7f8c8d;\">주문 번호</td>")
                .append("<td style=\"padding: 10px; border-bottom: 1px solid #ddd; color: #34495e;\">")
                .append(orderId)
                .append("</td>")
                .append("</tr>")
                .append("<tr>")
                .append("<td style=\"padding: 10px; border-bottom: 1px solid #ddd; color: #7f8c8d;\">배송 일자</td>")
                .append("<td style=\"padding: 10px; border-bottom: 1px solid #ddd; color: #34495e;\">")
                .append(deliveryDate)
                .append("</td>")
                .append("</tr>")
                .append("</table>")
                .append("</td>")
                .append("</tr>")
                .append("<tr>")
                .append("<td style=\"text-align: center; padding: 20px;\">")
                .append("<p style=\"font-size: 14px; color: #95a5a6;\">문의 사항이 있으시면 고객센터로 연락주시기 바랍니다.</p>")
                .append("<p style=\"font-size: 14px; color: #95a5a6;\">감사합니다!</p>")
                .append("</td>")
                .append("</tr>")
                .append("</table>")
                .append("</body>")
                .append("</html>");

        return sb.toString();
    }
}
