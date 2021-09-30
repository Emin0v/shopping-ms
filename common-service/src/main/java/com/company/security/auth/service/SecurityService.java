package com.company.security.auth.service;

import java.util.Optional;

import com.company.security.constants.UserAuthority;
import com.company.security.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public Optional<String> getCurrentUserEmail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }


    public Optional<String> getCurrentUserUuid() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> (CustomSpringSecurityUser) authentication.getCredentials())
                .map(CustomSpringSecurityUser::getUserUuid);
    }


    public Optional<UserAuthority> getCurrentUserAuthority() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return getAuthority(securityContext.getAuthentication());
    }


    public UserAuth getUserAuth() {
        return UserAuth
                .builder()
                .uuid(getCurrentUserUuid().orElseThrow(UserNotFoundException::new))
                .authority(getCurrentUserAuthority().orElseThrow(UserNotFoundException::new))
                .build();
    }

    public Optional<String> getCurrentUserJwt() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }

    /**
     * If the current user has a specific authority (security role). The name of this method comes from the {@code
     * isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public boolean isCurrentUserInRole(UserAuthority authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null)
                && getAuthority(authentication).stream().anyMatch(authority::equals);
    }

    private Optional<UserAuthority> getAuthority(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(Object::toString)
                .findFirst()
                .map(UserAuthority::valueOf);
    }
}
