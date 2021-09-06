package com.company.config;

import com.company.security.auth.service.JwtService;
import com.company.security.auth.service.TokenAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenAuthConfiguration {

    @Bean
    public TokenAuthService tokenAuthService(JwtService jwtService) {
        return new TokenAuthService(jwtService);
    }
}
