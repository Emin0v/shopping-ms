package com.company.security.auth.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomSpringSecurityUser extends User {

    @Setter
    @Getter
    private String userUuid;

    public CustomSpringSecurityUser(String email,
                                    String password,
                                    Collection<? extends GrantedAuthority> authorities,
                                    String userUuid){
        super(email, password, authorities);
        this.userUuid = userUuid;
    }
}
