package com.company.api;

import com.company.dto.BaseApiResponse;
import com.company.dto.LoginDTO;
import com.company.dto.RegisterDTO;
import com.company.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<BaseApiResponse> login(@Valid LoginDTO loginDTO){
//        return ResponseEntity.ok()
//    }

    @PostMapping("/register")
    public ResponseEntity<BaseApiResponse> register(@RequestBody RegisterDTO dto){
          return ResponseEntity.ok(authService.register(dto));
    }

}
