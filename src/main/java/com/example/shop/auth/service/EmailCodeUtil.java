package com.example.shop.auth.service;

import org.thymeleaf.context.Context;

import java.security.SecureRandom;

import static com.example.shop.global.util.TemplateEnginUtil.templateEngine;

public final class EmailCodeUtil {

    static String generateEmailCode() {
        // 숫자 (0-9), 대문자 (A-Z), 소문자 (a-z)
        String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int codeLength = 6;

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder authCode = new StringBuilder();

        for (int i = 0; i < codeLength; i++) {
            int randomIndex = secureRandom.nextInt(charSet.length());
            authCode.append(charSet.charAt(randomIndex));
        }

        return authCode.toString();
    }

    static String authCodeTemplate(String authCode) {
        Context context = new Context();
        context.setVariable("authCode", authCode);
        return templateEngine.process("auth-code-email", context);
    }
}
