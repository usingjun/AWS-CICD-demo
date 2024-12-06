package com.example.shop.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDeliveryRequest {

    @Email(message = "이메일 형식이 맞지 않습니다.")
    @NotBlank(message = "공백일 수 없습니다. 이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "공백일 수 없습니다. 주문 번호를 입력해주세요.")
    private String orderNumber;
}
