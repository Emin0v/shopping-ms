package com.company.service;

import com.company.dto.JwtTokenDto;
import com.company.dto.LoginRequestDto;
import com.company.dto.RegisterRequestDto;
import com.company.utilities.results.Result;

public interface AuthService {

    Result register(RegisterRequestDto registerRequestDto);

    JwtTokenDto login(LoginRequestDto loginRequestDto);

}
