package com.company.security;

import com.company.dto.LoginRequestDto;
import com.company.entity.User;
import com.company.repo.UserRepository;
import com.company.security.auth.service.JwtService;
import com.company.security.auth.service.SecurityService;
import com.company.security.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Import(SecurityService.class)
public class SecurityUtil {

    private final static Duration ONE_DAY = Duration.ofDays(1);
    private final static Duration ONE_WEEK = Duration.ofDays(7);

    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public User getCurrentUser(){
        return securityService.getCurrentUserEmail()
                .map(userRepository::findByEmail)
                .map(Optional::get)
                .orElseThrow(UserNotFoundException::new);
    }

    public String createAuthentication(LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtService.issueToken(authentication, rememberMe(loginRequestDto.getRememberMe()));
    }

    private Duration rememberMe(Boolean rememberMe){
        return ((rememberMe!=null) && rememberMe) ? ONE_WEEK : ONE_DAY;
    }

}
