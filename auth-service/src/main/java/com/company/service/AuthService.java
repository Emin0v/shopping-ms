package com.company.service;

import com.company.dto.BaseApiResponse;
import com.company.dto.LoginDTO;
import com.company.dto.RegisterDTO;

public interface AuthService {

    BaseApiResponse register(RegisterDTO registerDTO);

    BaseApiResponse login(LoginDTO loginDTO);

}
