package com.example.shop.auth.controller;

import com.example.shop.auth.dto.SignInResponse;
import com.example.shop.auth.dto.SignUpRequest;
import com.example.shop.auth.dto.SignUpResponse;
import com.example.shop.auth.service.AuthService;
import com.example.shop.auth.dto.SignInRequest;
import com.example.shop.domain.user.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name="Auth")
@RestController()
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) { return authService.signUp(signUpRequest, Role.ROLE_USER); }
    @Operation(summary = "회원가입")
    @PostMapping("/sign-up/admin")
    public SignUpResponse signUpAdmin(@RequestBody SignUpRequest signUpRequest) { return authService.signUp(signUpRequest, Role.ROLE_ADMIN); }

    @GetMapping("/test/user")
    public String testUser() {
        return "user";
    }
    @GetMapping("/test/admin")
    public String testAdmin() {
        return "admin";
    }

}
