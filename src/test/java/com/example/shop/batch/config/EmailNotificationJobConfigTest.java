package com.example.shop.batch.config;

import com.example.shop.admin.dao.OrderDeliveryRepository;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.global.setting.BatchTest;
import com.example.shop.global.util.EmailSender;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKeyYesterday;

@Sql(scripts = "classpath:/sql/order-dump.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EmailNotificationJobConfigTest extends BatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Job deliveryNotificationJob;

    @MockBean
    private EmailSender emailSender;

    @BeforeEach
    void setup() {
        for (long i = 1; i <= 567; i++) {
            orderDeliveryRepository.addOrderEmail(getOrderKeyYesterday(),
                    new OrderDeliveryRequest("test@test.com", i+"test"));
        }
    }

    @DisplayName("주문 배송 배치 처리 테스트")
    @Test
    void deliveryNotificationJob() throws Exception {
        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
            softAssertions.assertThat(orderRepository.countByOrderStatus(OrderStatus.SHIPPING)).isEqualTo(567);
        });
    }
}