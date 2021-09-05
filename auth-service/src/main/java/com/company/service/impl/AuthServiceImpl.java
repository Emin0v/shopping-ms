package com.company.service.impl;

import com.company.dto.JwtTokenDto;
import com.company.dto.LoginRequestDto;
import com.company.dto.RegisterRequestDto;
import com.company.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    @Override
    public void register(RegisterRequestDto registerRequestDto) {
    }

    @Override
    public JwtTokenDto login(LoginRequestDto loginRequestDto) {
        return null;
    }
}
