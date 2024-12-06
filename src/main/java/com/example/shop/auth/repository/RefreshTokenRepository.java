package com.example.shop.auth.repository;

import com.example.shop.domain.user.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMemberId(Long memberId);
    void deleteByMemberId(Long memberId);

}
