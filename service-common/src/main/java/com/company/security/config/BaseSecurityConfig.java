package com.company.security.config;

import com.company.security.auth.AuthFilterConfigurerAdapter;
import com.company.security.auth.service.AuthService;
import com.company.security.constants.UserAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.StringJoiner;

@RequiredArgsConstructor
public abstract class BaseSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ACTUATOR = "/actuator/**";
    private static final String SWAGGER = "/v2/api-docs";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_HTML = "/swagger-ui.html";

    private final SecurityProperties securityProperties;
    private final List<AuthService> authServices;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource());
        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        //Disallow all requests by default unless explicitly defined in submodules
        http.authorizeRequests().antMatchers(ACTUATOR).permitAll();
        http.authorizeRequests().anyRequest().access(authorities(UserAuthority.ADMIN.name()));
        // Apply AuthRequestFilter
        http.apply(new AuthFilterConfigurerAdapter(authServices));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/**", "/swagger-ui.html",
                "/webjars/**", "/csrf", "/");
    }

    protected String authority(String role) {
        return "hasAuthority('" + role + "')";
    }

    protected String authority(UserAuthority role) {
        return "hasAuthority('" + role.name() + "')";
    }

    protected String authorities(Object... roles) {
        StringJoiner joiner = new StringJoiner(" or ");
        for (Object role : roles) {
            if (role instanceof UserAuthority) {
                joiner.add(authority((UserAuthority) role));
            } else {
                joiner.add(authority(role.toString()));
            }
        }
        return joiner.toString();
    }

    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = securityProperties.getCors();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
