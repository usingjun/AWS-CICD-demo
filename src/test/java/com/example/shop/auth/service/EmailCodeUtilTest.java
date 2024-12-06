package com.example.shop.auth.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.shop.auth.service.EmailCodeUtil.generateEmailCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailCodeUtilTest {

    @DisplayName("Thymeleaf를 이용해 html 파일을 String으로 변환")
    @Test
    void authCodeTemplate() {
        // given
        String authCode = generateEmailCode();

        // when & then
        assertThat(EmailCodeUtil.authCodeTemplate(authCode)).contains(authCode);
    }
}