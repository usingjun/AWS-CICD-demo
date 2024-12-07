package com.example.shop.global.annotation;

import com.example.shop.admin.controller.AdminOrderController;
import com.example.shop.admin.controller.AdminProductController;
import com.example.shop.admin.service.AdminOrderService;
import com.example.shop.admin.service.AdminProductService;
import com.example.shop.auth.controller.AuthController;
import com.example.shop.auth.service.AuthService;
import com.example.shop.global.config.auth.JwtAuthenticationFilter;
import com.example.shop.user.controller.CartController;
import com.example.shop.user.controller.OrderController;
import com.example.shop.user.controller.ProductController;
import com.example.shop.user.service.CartService;
import com.example.shop.user.service.OrderService;
import com.example.shop.user.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebMvcTest({
        AdminOrderController.class,
        AdminProductController.class,
        AuthController.class,
        CartController.class,
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    protected AdminOrderService adminOrderService;

    @MockBean
    protected AdminProductService adminProductService;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected CartService cartService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected ProductService productService;
}
