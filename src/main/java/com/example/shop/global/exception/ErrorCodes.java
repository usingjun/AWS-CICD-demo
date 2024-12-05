package com.example.shop.global.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다"),
    NO_AUTH_ACCESS_TOKEN(HttpStatus.BAD_REQUEST,"액세스 토큰이 존재하지 않습니다"),
    NO_AUTHORIZATION_HEADER(HttpStatus.BAD_REQUEST,"Authorization이 존재하지 않습니다"),
    NO_BEARER(HttpStatus.BAD_REQUEST,"bearer 타입 토큰이 존재하지 않습니다"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT,"중복 이메일입니다"),
    LOG_IN_NOT_MATCH(HttpStatus.BAD_REQUEST, "로그인 정보가 일치하지 않습니다"),
    REFRESH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST,"리프레시 토큰이 만료되었습니다. 다시 로그인해주세요"),
    NO_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "인증 정보가 존재하지 않습니다"),

    // 장바구니 관련
    INVALID_CART_QUANTITY(HttpStatus.BAD_REQUEST, "수량은 1개 이상이어야 합니다."),
    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "등록된 상품이 아닙니다."),
    CART_PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "장바구니에 해당 상품이 없습니다."),
    EMPTY_CART(HttpStatus.BAD_REQUEST, "장바구니가 비어있습니다."),

    // 주문 관련
    EMPTY_ORDER_DETAIL(HttpStatus.BAD_REQUEST, "주문 상세 내역이 존재하지 않습니다."),
    ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST, "주문 내역이 존재하지 않습니다."),
    NOT_MODIFIABLE_ORDER(HttpStatus.BAD_REQUEST, "주문을 수정할 수 없는 상태입니다."),
    ORDER_DETAIL_NOT_FOUND(HttpStatus.BAD_REQUEST, "주문 상세 내역을 찾을수 없습니다."),
    UNAUTHORIZED_ORDER_ACCESS(HttpStatus.BAD_REQUEST, "주문자 본인만 접근할 수 있습니다."),

    // 재고 관련
    NOT_ENOUGH_QUANTITY(HttpStatus.BAD_REQUEST, "상품 수량이 부족합니다."),

    // Email 관련 에러
    EMAIL_FORM_CREATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 템플릿 생성 중 오류가 발생했습니다."),
    EMAIL_SEND_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "Email 서버에 문제가 발생하였습니다."),

    // 메뉴 조회 관련 에러
    PRODUCTS_EMPTY(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."),
    PRODUCTID_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다."),
    PRODUCTS_SEARCH_EMPTY(HttpStatus.NO_CONTENT, "조건에 맞는 상품이 없습니다.");


    private String message;
    private HttpStatus status;
    ErrorCodes(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }
}
