package com.example.shop.global.config.auth;

import com.example.shop.domain.user.UserRepository;
import com.example.shop.domain.user.User;
import com.example.shop.global.exception.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return createUserDetails(userRepository.findByEmail(email)
                .orElseThrow(UserNotFound::new));
    }

    public UserDetails createUserDetails(User user) {
        UserDetails userDetails =  org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getUserRole().toString()))
                .build();
        return userDetails;
    }
}
