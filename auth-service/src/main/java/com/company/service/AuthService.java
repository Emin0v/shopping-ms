package com.company.service;

import com.company.dto.JwtTokenDto;
import com.company.dto.LoginRequestDto;
import com.company.dto.RegisterRequestDto;

public interface AuthService {

    void register(RegisterRequestDto registerRequestDto);

    JwtTokenDto login(LoginRequestDto loginRequestDto);

}
