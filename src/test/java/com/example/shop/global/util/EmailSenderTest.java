package com.example.shop.global.util;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.global.config.RedisTestConfig;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.shop.global.util.TemplateEnginUtil.templateEngine;

@SpringBootTest
@ActiveProfiles("test")
@Import(RedisTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class EmailSenderTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("testMail", "testMail"))
            .withPerMethodLifecycle(true);  // 테스트 메서드마다 새로운 GreenMail 인스턴스 사용

    @Autowired
    private EmailSender emailSender;

    @DisplayName("이메일 인증 코드 송수신 테스트")
    @Test
    void sendAuthCode() {
        // given
        String receivedEmail = "receivedmail@test.com";
        String subject = "test subject";
        String content = authCodeTemplate("test authCode");

        // when
        emailSender.sendMail(receivedEmail, subject, content);
        greenMail.waitForIncomingEmail(1);  // 비동기로 동작하기 때문에 대기함

        //then
        verifyReceivedEmail(receivedEmail, subject, content);
    }

    @DisplayName("주문 배송 알림 이메일 송수신 테스트")
    @Test
    void sendDeliveryAlertEmail() {
        // given
        OrderDeliveryRequest order = new OrderDeliveryRequest("tester@test.com", "Test Order Number");
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        String receivedEmail = "tester@test.com";
        String subject = "test subject";
        String content = orderDeliveryTemplate(order);

        // when
        emailSender.sendMail(receivedEmail, subject, content);
        greenMail.waitForIncomingEmail(1);  // 비동기로 동작하기 때문에 대기함

        //then
        verifyReceivedEmail(receivedEmail, subject, content);
    }

    private void verifyReceivedEmail(String receivedEmail, String subject, String content) {
        QuotedPrintableCodec codec = new QuotedPrintableCodec("UTF-8"); // 한글을 디코딩하기 위함
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();

        SoftAssertions.assertSoftly(softAssertions -> {
            try {
                softAssertions.assertThat(receivedMessages[0].getAllRecipients()[0].toString())
                        .isEqualTo(receivedEmail);

                softAssertions.assertThat(receivedMessages[0].getSubject())
                        .isEqualTo(subject);

                softAssertions.assertThat(codec.decode(GreenMailUtil.getBody(receivedMessages[0])))
                        .isEqualToIgnoringNewLines(content);

                softAssertions.assertThatThrownBy(() -> GreenMailUtil.getBody(receivedMessages[1]))
                        .isExactlyInstanceOf(ArrayIndexOutOfBoundsException.class);

            } catch (MessagingException | DecoderException e) {
                e.printStackTrace();
            }
        });
    }

    private String authCodeTemplate(String authCode) {
        Context context = new Context();
        context.setVariable("authCode", authCode);
        return templateEngine.process("auth-code-email", context);
    }

    private String orderDeliveryTemplate(OrderDeliveryRequest order) {
        String orderNumber = order.getOrderNumber();
        String deliveryDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));

        Context context = new Context();
        context.setVariable("orderNumber", orderNumber);
        context.setVariable("deliveryDate", deliveryDate);
        return templateEngine.process("delivery-email", context);
    }
}