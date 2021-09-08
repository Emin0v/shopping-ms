package com.company.config;

import java.util.List;

import com.company.security.auth.AuthenticationEntryPointConfigurer;
import com.company.security.auth.service.JwtService;
import com.company.security.auth.service.TokenAuthService;
import com.company.security.config.BaseSecurityConfig;
import com.company.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.company.constants.HttpConstants.SUB_PATH;
import static com.company.security.constants.UserAuthority.ADMIN;
import static com.company.security.constants.UserAuthority.USER;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import({SecurityProperties.class, JwtService.class, AuthenticationEntryPointConfigurer.class})
public class SecurityConfiguration extends BaseSecurityConfig {
    private static final String AUTH_API = "/api/auth";
    private static final String USER_API = "/api/users";

    public SecurityConfiguration(SecurityProperties securityProperties, JwtService jwtService) {
        super(securityProperties, List.of(new TokenAuthService(jwtService)));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(AUTH_API + SUB_PATH).permitAll()
                .antMatchers(USER_API + SUB_PATH).access(authorities(USER));

        super.configure(http);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
