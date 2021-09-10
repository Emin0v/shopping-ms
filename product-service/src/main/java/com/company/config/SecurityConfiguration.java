package com.company.config;

import java.util.List;

import com.company.security.auth.AuthenticationEntryPointConfigurer;
import com.company.security.auth.service.AuthService;
import com.company.security.auth.service.JwtService;
import com.company.security.config.BaseSecurityConfig;
import com.company.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static com.company.constants.HttpConstants.SUB_PATH;
import static com.company.security.constants.UserAuthority.ADMIN;
import static com.company.security.constants.UserAuthority.USER;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Import({
        SecurityProperties.class, JwtService.class,
        AuthenticationEntryPointConfigurer.class
})
@EnableWebSecurity
public class SecurityConfiguration extends BaseSecurityConfig {
    private static final String PRODUCT_API = "/api/products";
    private static final String CATEGORY_API = "/api/category";

    public SecurityConfiguration(SecurityProperties securityProperties, List<AuthService> authServices) {
        super(securityProperties, authServices);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(GET, CATEGORY_API + SUB_PATH).permitAll()
                .antMatchers(GET, PRODUCT_API + SUB_PATH).permitAll()
                .antMatchers(PRODUCT_API + SUB_PATH).access(authorities(USER))
                .antMatchers(CATEGORY_API + SUB_PATH).access(authorities(USER));

        super.configure(http);
    }
}
