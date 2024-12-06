package com.example.shop.auth.controller;

import com.example.shop.auth.dto.*;
import com.example.shop.auth.service.AuthService;
import com.example.shop.domain.user.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Auth")
@RestController()
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<AccessTokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.signUp(signUpRequest, Role.ROLE_USER));
    }
    @Operation(summary = "회원가입")
    @PostMapping("/sign-up/admin")
    public ResponseEntity<SignUpResponse> signUpAdmin(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.signUp(signUpRequest, Role.ROLE_ADMIN));
    }

    @GetMapping("/test/user")
    public String testUser() {
        return "user";
    }
    @GetMapping("/test/admin")
    public String testAdmin() {
        return "admin";
    }

    @Operation(summary = "이메일 인증 코드 발송")
    @PostMapping("/email/auth-code")
    public ResponseEntity<Void> sendEmailCode(@RequestBody @Valid EmailCodeRequest email) {
        authService.sendAuthCode(email.getEmail());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증 코드 검증")
    @PostMapping("/auth-code/verification")
    public ResponseEntity<Void> verifyEmailCode(@RequestBody @Valid EmailCodeRequest emailCodeRequest) {
        boolean isValid = authService.verifyAuthCode(emailCodeRequest);
        if (isValid) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @Operation(summary = "액세스 토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<AccessTokenResponse> reissue(@RequestBody String accessToken) {
        return ResponseEntity.ok(authService.reissue(accessToken));
    }

    @Operation(summary = "회원탈퇴")
    @DeleteMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(@RequestBody SignInRequest signInRequest) {
        authService.deleteUser(signInRequest);
        return ResponseEntity.ok().build();
    }
}
