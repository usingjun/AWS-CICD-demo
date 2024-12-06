package com.example.shop.domain.order;

import com.example.shop.admin.dto.OrderSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {

    public Page<Order> searchOrders(OrderSearchRequest request, Pageable pageable);
}
