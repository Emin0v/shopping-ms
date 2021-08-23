package com.company.service.impl;

import com.company.dto.BaseApiResponse;
import com.company.dto.LoginDTO;
import com.company.dto.RegisterDTO;
import com.company.entity.Role;
import com.company.entity.User;
import com.company.repo.RoleRepository;
import com.company.repo.UserRepository;
import com.company.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseApiResponse register(RegisterDTO dto) {
        try {
            Optional<Role> role = roleRepository.findByRole("ROLE_USER");

            User user = User.builder()
                    .name(dto.getName())
                    .surname(dto.getSurname())
                    .active(1)
                    .email(dto.getEmail())
                    .isEnabled(true)
                    .isExpired(false)
                    .isLocked(false)
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(Set.of(role.get()))
                    .build();

            userRepository.save(user);

            return BaseApiResponse.builder()
                    .message("User has been registered successfully")
                    .status(HttpStatus.CREATED.value())
                    .build();

        } catch (Exception ex) {
            ex.printStackTrace();
            return BaseApiResponse.builder()
                    .message("Internal Server Error")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();

        }
    }

    @Override
    public BaseApiResponse login(LoginDTO loginDTO) {
        return null;
    }
}
