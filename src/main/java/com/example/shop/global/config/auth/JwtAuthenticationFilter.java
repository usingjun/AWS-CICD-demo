package com.example.shop.global.config.auth;

import com.example.shop.auth.repository.RefreshTokenRepository;
import com.example.shop.global.exception.NoAuthorizationHeader;
import com.example.shop.global.exception.NoBearer;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final static String EXCEPTION = "exception";
    private final static String LOGOUT = "logout";
    private final static String MALFORMED = "malformed";
    private final static String EXPIRED = "expired";
    private final static String HEADER = "header";
    private final static String BEARER = "bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = resolveToken(request);
            if (jwtProvider.validateToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (MalformedJwtException e) {
            request.setAttribute(EXCEPTION,MALFORMED);
        } catch (ExpiredJwtException e) {
            request.setAttribute(EXCEPTION, EXPIRED);
        } catch (NoAuthorizationHeader e) {
            request.setAttribute(EXCEPTION, HEADER);
        } catch (NoBearer e) {
            request.setAttribute(EXCEPTION,BEARER);
        } finally {
            filterChain.doFilter(request, response);
        }

    }

    //헤더에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(!StringUtils.hasText(bearerToken)) throw new NoAuthorizationHeader();
        if(!bearerToken.startsWith("Bearer")) throw new NoBearer();
        return bearerToken.substring(7);
    }
}
