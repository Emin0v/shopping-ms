package com.company.security.auth.service;

import java.util.Optional;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest);
}
