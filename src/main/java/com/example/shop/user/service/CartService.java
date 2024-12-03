package com.example.shop.user.service;

import com.example.shop.domain.cart.CartDetail;
import com.example.shop.domain.cart.CartDetailRepository;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.global.exception.ProductNotFoundException;
import com.example.shop.global.exception.UserNotFound;
import com.example.shop.user.dto.AddCartProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public void addCartProduct(String userEmail, AddCartProductRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(UserNotFound::new);

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        CartDetail cartDetail = new CartDetail(user, product, request.getQuantity());
        cartDetailRepository.save(cartDetail);
    }
}
