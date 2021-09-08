package com.company.service.providers;

import com.company.security.auth.service.Claim;
import com.company.security.auth.service.ClaimProvider;
import com.company.security.auth.service.CustomSpringSecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserProvider implements ClaimProvider {
    private static final String RULE = "userUuid";

    @Override
    public Claim provide(Authentication authentication) {
        log.trace("Providing {} claims", RULE);
        var principal = (CustomSpringSecurityUser) authentication.getPrincipal();
        return new Claim(RULE, principal.getUserUuid());
    }
}
