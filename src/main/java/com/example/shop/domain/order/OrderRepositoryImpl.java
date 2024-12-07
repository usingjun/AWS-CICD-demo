package com.example.shop.domain.order;

import com.example.shop.admin.dto.OrderSearchRequest;
import com.example.shop.domain.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Order> searchOrders(OrderSearchRequest request, Pageable pageable) {
        QOrder order = QOrder.order;
        QUser user = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();

        // 검색 조건 설정
        // 주문자 이름 검색
        if (StringUtils.hasText(request.getOrderName())) {
            builder.and(user.userName.contains(request.getOrderName()));
        }

        // 수령자 이름 검색
        if (StringUtils.hasText(request.getReceiverName())) {
            builder.and(order.deliveryInfo.receiverName.contains(request.getReceiverName()));
        }

        // 결제 금액 범위 검색
        if (request.getMinPrice() != null) {
            builder.and(order.totalPrice.goe(request.getMinPrice()));
        }
        if (request.getMaxPrice() != null) {
            builder.and(order.totalPrice.loe(request.getMaxPrice()));
        }

        // 주문 일자 검색
        if (request.getOrderDateStart() != null) {
            builder.and(order.createdAt.goe(request.getOrderDateStart().atStartOfDay()));
        }
        if (request.getOrderDateEnd() != null) {
            builder.and(order.createdAt.loe(request.getOrderDateEnd().atTime(23, 59, 59)));
        }

        // 수정 일자 검색
        if (request.getModifiedDateStart() != null) {
            builder.and(order.updatedAt.goe(request.getModifiedDateStart().atStartOfDay()));
        }
        if (request.getModifiedDateEnd() != null) {
            builder.and(order.updatedAt.loe(request.getModifiedDateEnd().atTime(23, 59, 59)));
        }

        // 데이터 조회
        List<Order> result = queryFactory
                .selectFrom(order)
                .join(order.user, user).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(
                        order.updatedAt.desc(),
                        order.createdAt.desc()
                )
                .fetch();

        // 전체 카운트 조회
        long total = queryFactory
                .select(order.count())
                .from(order)
                .join(order.user, user)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }
}
