package com.example.shop.global.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다"),
    NO_AUTH_ACCESS_TOKEN(HttpStatus.BAD_REQUEST,"액세스 토큰이 존재하지 않습니다"),
    NO_AUTHORIZATION_HEADER(HttpStatus.BAD_REQUEST,"Authorization이 존재하지 않습니다"),
    NO_BEARER(HttpStatus.BAD_REQUEST,"bearer 타입 토큰이 존재하지 않습니다"),
    LOG_IN_NOT_MATCH(HttpStatus.BAD_REQUEST, "로그인 정보가 일치하지 않습니다");


    private String message;
    private HttpStatus status;
    ErrorCodes(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }
}
