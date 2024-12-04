package com.example.shop.user.service;

import com.example.shop.domain.cart.CartDetail;
import com.example.shop.domain.cart.CartDetailRepository;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.global.exception.CartEmptyException;
import com.example.shop.global.exception.CartProductNotFoundException;
import com.example.shop.global.exception.ProductNotFoundException;
import com.example.shop.global.exception.UserNotFoundException;
import com.example.shop.global.util.SecurityUtil;
import com.example.shop.user.dto.AddCartProductRequest;
import com.example.shop.user.dto.CartDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addCartProduct(AddCartProductRequest request) {
        User user = getCurrentUser();

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        // 기존 장바구니에 같은 상품이 있는지 확인
        Optional<CartDetail> existingCart = cartDetailRepository.findByUserIdAndProductId(user.getId(), product.getId());

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

    public List<CartDetailResponse> getCartDetails() {
        User user = getCurrentUser();

        List<CartDetail> cartDetails = cartDetailRepository.findByUserIdWithProduct(user.getId());
        return cartDetails.stream()
                .map(cartDetail -> new CartDetailResponse(cartDetail.getProduct().getId(),
                        cartDetail.getProduct().getProductName(),
                        cartDetail.getProduct().getPrice(),
                        cartDetail.getQuantity()))
                .toList();
    }

    @Transactional
    public void removeCartProduct(Long productId) {
        User user = getCurrentUser();

        // 삭제할 상품이 장바구니에 있는지 확인
        CartDetail cartDetail = cartDetailRepository.findByUserIdAndProductId(user.getId(), productId)
                                                    .orElseThrow(CartProductNotFoundException::new);

        cartDetailRepository.delete(cartDetail);
    }

    @Transactional
    public void removeAllCartProducts() {
        User user = getCurrentUser();
        List<CartDetail> cartDetails = cartDetailRepository.findByUserId(user.getId());

        // 장바구니가 비어있는지 확인
        if (cartDetails.isEmpty()) {
            throw new CartEmptyException();
        }

        cartDetailRepository.deleteAllByUserId(user.getId());
    }


    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

}
