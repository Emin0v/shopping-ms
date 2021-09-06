package com.company.api;

import com.company.dto.JwtTokenDto;
import com.company.dto.LoginRequestDto;
import com.company.dto.RegisterRequestDto;
import com.company.service.AuthService;
import com.company.service.UserService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.AuthCtrl.CTRL)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Validated RegisterRequestDto registerRequestDto) {
        log.trace("Register user: {}", registerRequestDto);
        authService.register(registerRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody @Validated LoginRequestDto loginRequestDto) {
        log.trace("Login user: {}", loginRequestDto);
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


}