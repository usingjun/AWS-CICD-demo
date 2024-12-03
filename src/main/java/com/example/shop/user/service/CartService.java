package com.example.shop.user.service;

import com.example.shop.domain.cart.CartDetail;
import com.example.shop.domain.cart.CartDetailRepository;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.global.exception.ProductNotFoundException;
import com.example.shop.global.exception.UserNotFoundException;
import com.example.shop.user.dto.AddCartProductRequest;
import com.example.shop.user.dto.CartDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public void addCartProduct(String userEmail, AddCartProductRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        // 기존 장바구니에 같은 상품이 있는지 확인
        Optional<CartDetail> existingCart = cartDetailRepository.findByUserAndProduct(user, product);

        if (existingCart.isPresent()) {
            // 기존 상품이 있으면 수량 추가
            CartDetail cartDetail = existingCart.get();
            cartDetail.changeQuantity(cartDetail.getQuantity() + request.getQuantity());
        } else {
            // 새로운 상품이면 새로 추가
            CartDetail cartDetail = new CartDetail(user, product, request.getQuantity());
            cartDetailRepository.save(cartDetail);
        }

    }

    public List<CartDetailResponse> getCartDetails(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        List<CartDetail> cartDetails = cartDetailRepository.findByUserIdWithProduct(user.getId());
        return cartDetails.stream()
                .map(cartDetail -> new CartDetailResponse(cartDetail.getId(),
                        cartDetail.getProduct().getProductName(),
                        cartDetail.getProduct().getPrice(),
                        cartDetail.getQuantity()))
                .toList();
    }

}
