package com.example.shop.batch;

import com.example.shop.admin.dao.OrderDeliveryRepository;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.admin.mapper.AdminMapper;
import com.example.shop.admin.service.AdminOrderService;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.global.annotation.ServiceTest;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKeyYesterday;
import static org.mockito.Mockito.doNothing;


@ExtendWith(MockitoExtension.class)
@Sql(scripts = "classpath:/sql/order-data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class OrderUpdateSenderWriterTest extends ServiceTest {

    @MockBean
    private AdminOrderService adminOrderService;
    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        for (long i = 1; i <= 5; i++) {
            orderDeliveryRepository.addOrderEmail(getOrderKeyYesterday(),
                    new OrderDeliveryRequest("test@test.com", i+"test"));
        }
    }

    @DisplayName("주문 상태값 배송으로 변경 시, Mybatis로 Batch 작업")
    @Test
    void write() {
        List<OrderDeliveryRequest> orderDeliveryRequestList = orderDeliveryRepository.findAllOrder(getOrderKeyYesterday()).stream().toList();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

            orderDeliveryRequestList.forEach(adminMapper::updateOrderDelivery);
            sqlSession.flushStatements();

        } finally {
            orderDeliveryRequestList.forEach(doNothing().when(adminOrderService)::sendDeliveryAlertEmail);
            orderDeliveryRepository.removeOrderEmail(getOrderKeyYesterday(), orderDeliveryRequestList.toArray());
        }

        SoftAssertions.assertSoftly(softAssertions -> {
            Order order = orderRepository.findByOrderNumber(orderDeliveryRequestList.get(2).getOrderNumber())
                            .orElseThrow(NullPointerException::new);

            softAssertions.assertThat(order.getOrderStatus())
                    .isEqualTo(OrderStatus.SHIPPING);

            softAssertions.assertThat(order.getUpdatedAt())
                    .isNotEqualTo(order.getCreatedAt());
        });
    }
}