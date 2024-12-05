package com.example.shop.batch;

import com.example.shop.admin.dao.OrderDeliveryRepository;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.admin.mapper.AdminMapper;
import com.example.shop.admin.service.OrderAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKey;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderUpdateSenderWriter implements ItemWriter<OrderDeliveryRequest> {

    private final OrderAdminService orderAdminService;
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final SqlSessionFactory sqlSessionFactory;

    @Transactional
    @Override
    public void write(Chunk<? extends OrderDeliveryRequest> chunk) throws Exception {
        @SuppressWarnings("unchecked")
        List<OrderDeliveryRequest> orderDeliveryRequestList = (List<OrderDeliveryRequest>) chunk.getItems();
        log.info(String.valueOf(orderDeliveryRequestList.size()));
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

            orderDeliveryRequestList.forEach(adminMapper::updateOrderDelivery);
            sqlSession.flushStatements();

        } finally {
            orderDeliveryRequestList.forEach(orderAdminService::sendDeliveryAlertEmail);
            orderDeliveryRepository.removeOrderEmail(getOrderKey(), orderDeliveryRequestList.toArray());
        }
    }
}
