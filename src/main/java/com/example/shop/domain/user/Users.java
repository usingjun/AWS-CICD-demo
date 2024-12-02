package com.example.shop.domain.user;

import com.example.shop.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity {
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
}
