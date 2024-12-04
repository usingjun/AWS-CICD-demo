package com.example.shop.domain.user;

import com.example.shop.domain.BaseEntity;
import com.example.shop.domain.cart.CartDetail;
import com.example.shop.domain.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Column(name = "user_name")
    private String userName;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role userRole;

    @OneToMany(mappedBy = "user")
    private List<CartDetail> cartDetails = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
