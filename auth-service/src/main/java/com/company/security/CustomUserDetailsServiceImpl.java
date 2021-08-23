package com.company.security;

import com.company.entity.User;
import com.company.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).get();

        if (user != null) {

            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);

            builder.disabled(false);
            builder.password(user.getPassword());

            List<String> roles = user.getRole().stream().map(role -> {
                return role.getRole();
            }).collect(Collectors.toList());

            builder.authorities(roles.toArray(new String[0]));

            return builder.build();
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
