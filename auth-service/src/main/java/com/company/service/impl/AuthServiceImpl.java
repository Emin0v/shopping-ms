package com.company.service.impl;

import com.company.client.CustomerServiceClient;
import com.company.dto.JwtTokenDto;
import com.company.dto.LoginRequestDto;
import com.company.dto.RegisterRequestDto;
import com.company.dto.customer.RegisterReqDto;
import com.company.entity.User;
import com.company.repo.UserRepository;
import com.company.security.SecurityUtil;
import com.company.service.AuthService;
import com.company.service.mapper.UserMapper;
import com.company.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.company.security.constants.UserAuthority.USER;
import static com.company.security.constants.UserStatus.ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;
    private final CustomerServiceClient customerServiceClient;

    @Override
    public Result register(RegisterRequestDto registerRequestDto) {
        Optional<User> userOptional = userRepository.findByEmail(registerRequestDto.getEmail());
        if (userOptional.isPresent()){
            return new Result(false,"Email already exist");
        }
        User user = userMapper.registerDtoToEntity(registerRequestDto);
        user.setAuthority(USER);
        user.setStatus(ACTIVE);

        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        user = userRepository.save(user);

        return customerServiceClient.register(new RegisterReqDto(user.getUuid()));
    }

    @Override
    public JwtTokenDto login(LoginRequestDto loginRequestDto) {
        String jwt = securityUtil.createAuthentication(loginRequestDto);

        return JwtTokenDto.builder().authToken(jwt).build();
    }
}
