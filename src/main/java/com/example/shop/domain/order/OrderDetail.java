package com.example.shop.domain.order;

import com.example.shop.domain.BaseEntity;
import com.example.shop.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    // 주문 상세 생성
    public static OrderDetail createOrderDetail(Product product, int quantity) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.product = product;
        orderDetail.quantity = quantity;
        return orderDetail;
    }

    // 연관관계 편의 메서드 사용을 위해 default 범위로 만듦
    void setOrder(Order order) {
        this.order = order;
    }
}
