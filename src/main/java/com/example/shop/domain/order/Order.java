package com.example.shop.domain.order;

import com.example.shop.domain.BaseEntity;
import com.example.shop.domain.user.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name="total_price")
    @NotNull
    private Long totalPrice;
    @Column(name = "receiver_postal_code")
    @NotNull
    private String receiverPostalCode;
    @Column(name = "receiver_name")
    @NotNull
    private String receiverName;
    @Column(name="receiver_address")
    @NotNull
    private String receiverAddress;
    @Column(name="receiver_phone")
    @NotNull
    private String receiverPhone;

    @Column(name = "shipping_message")
    private String shippingMessage;

    @Column(name = "order_state")
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderState orderState;


}
