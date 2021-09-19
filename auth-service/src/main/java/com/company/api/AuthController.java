package com.company.api;

import com.company.dto.JwtTokenDto;
import com.company.dto.LoginRequestDto;
import com.company.dto.RegisterRequestDto;
import com.company.service.AuthService;
import com.company.service.UserService;
import com.company.util.ApiPaths;
import com.company.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.AuthCtrl.CTRL)
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterRequestDto registerRequestDto) {
        log.trace("Register user: {}", registerRequestDto);
        return authService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody @Validated LoginRequestDto loginRequestDto) {
        log.trace("Login user: {}", loginRequestDto);
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


}