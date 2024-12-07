package com.example.shop.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearchRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDateEnd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate modifiedDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate modifiedDateEnd;

    private String orderName;      // 주문자
    private String receiverName;   // 수령자
    private BigDecimal minPrice;     // 최소 결제금액
    private BigDecimal maxPrice;     // 최대 결제금액
}
