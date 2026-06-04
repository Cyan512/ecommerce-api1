package com.ecommerce.auth.infrastructure.config;

import com.ecommerce.auth.domain.port.TokenPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBeanConfig {

    @Bean
    public TokenPort tokenPort(JwtUtil jwtUtil) {
        return jwtUtil;
    }
}
