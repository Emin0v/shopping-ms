package com.company.config;

import com.company.security.auth.AuthenticationEntryPointConfigurer;
import com.company.security.auth.service.AuthService;
import com.company.security.auth.service.JwtService;
import com.company.security.config.BaseSecurityConfig;
import com.company.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

import static com.company.constants.HttpConstants.SUB_PATH;
import static com.company.security.constants.UserAuthority.USER;

@Slf4j
@Import({
        SecurityProperties.class, JwtService.class,
        AuthenticationEntryPointConfigurer.class
})
@EnableWebSecurity
public class SecurityConfiguration extends BaseSecurityConfig {
    private static final String ORDER_API = "/api/orders";

    public SecurityConfiguration(SecurityProperties securityProperties, List<AuthService> authServices) {
        super(securityProperties, authServices);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ORDER_API + SUB_PATH).access(authorities(USER));

        super.configure(http);
    }
}
