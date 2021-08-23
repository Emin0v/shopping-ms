package com.company.repo;

import com.company.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

    Long deleteByToken(String token);

    JwtToken findByToken(String token);

}
