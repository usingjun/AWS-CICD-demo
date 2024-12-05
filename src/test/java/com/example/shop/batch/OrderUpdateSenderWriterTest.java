package com.example.shop.batch;

import com.example.shop.admin.dao.OrderDeliveryRepository;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.admin.mapper.AdminMapper;
import com.example.shop.admin.service.OrderAdminService;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.domain.user.Role;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKeyYesterday;

@Transactional
@SpringBootTest
class OrderUpdateSenderWriterTest {

    @Autowired
    private OrderAdminService orderAdminService;
    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .userRole(Role.ROLE_USER)
                .orders(new ArrayList<>())
                .userName("tester")
                .build();

        user = userRepository.save(user);

        for (long i = 335; i <= 568; i++) {
            orderDeliveryRepository.addOrderEmail(getOrderKeyYesterday(),
                    new OrderDeliveryRequest(user.getEmail(), i));
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
            orderDeliveryRequestList.forEach(orderAdminService::sendDeliveryAlertEmail);
            orderDeliveryRepository.removeOrderEmail(getOrderKeyYesterday(), orderDeliveryRequestList.toArray());
        }

        SoftAssertions.assertSoftly(softAssertions -> {
            Order order = orderRepository.findById(orderDeliveryRequestList.get(2).getOrderId())
                            .orElseThrow(NullPointerException::new);

            softAssertions.assertThat(order.getOrderStatus())
                    .isEqualTo(OrderStatus.SHIPPING);

            softAssertions.assertThat(order.getUpdatedAt())
                    .isNotEqualTo(order.getCreatedAt());
        });
    }
}