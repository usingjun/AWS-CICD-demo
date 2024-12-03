package com.example.shop.global.config;

import com.example.shop.auth.repository.RefreshTokenRepository;
import com.example.shop.global.config.auth.JwtAuthenticationEntryPoint;
import com.example.shop.global.config.auth.JwtAuthenticationFilter;
import com.example.shop.global.config.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    private final String[] WHITE_LIST = new String[]{
            "/swagger-ui/index.html",
            "/swagger-ui/index.html#/"
            ,"/swagger-ui.html"
            ,"/swagger-ui/**"
            ,"/api-docs/**"
            ,"/v3/api-docs/**",
            "/api/auth/sign-up/**",
            "/api/auth/sign-in",
            "/error",
            "/api/auth/reissue",
            "/api/auth/email/auth-code",
            "/api/auth/auth-code/verification"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic((httpBasic)->httpBasic.disable())
                .csrf((csrf)->csrf.disable())
                .sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeRequests)->
                        authorizeRequests
                                .requestMatchers(WHITE_LIST).permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling((exception) ->
                        exception.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider, refreshTokenRepository), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
