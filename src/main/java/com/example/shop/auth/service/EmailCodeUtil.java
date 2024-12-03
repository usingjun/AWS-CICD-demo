package com.example.shop.auth.service;

import java.security.SecureRandom;

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
        return """
                <!DOCTYPE html>
                    <html lang="ko">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>이메일 인증</title>
                    </head>
                    <body style="font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 0;">
                
                    <table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse; background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                        <tr>
                            <td align="center" bgcolor="#4caf50" style="padding: 20px 0; color: #ffffff; font-size: 24px; font-weight: bold;">
                                이메일 인증 코드
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 20px; text-align: center; color: #333333;">
                                <p style="margin: 10px 0;">안녕하세요, 귀하의 인증 코드는 아래와 같습니다:</p>
                                <div style="font-size: 36px; font-weight: bold; color: #4caf50; margin: 20px 0;">
                                    %s
                                </div>
                                <p style="margin: 10px 0;">이 코드를 입력하여 이메일 인증을 완료해주세요.</p>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" style="padding: 10px; font-size: 12px; color: #777777;">
                                <p style="margin: 0;">© DevCourse Team 7. All rights reserved.</p>
                            </td>
                        </tr>
                    </table>
                
                    </body>
                    </html>
            """.formatted(authCode);
    }
}
