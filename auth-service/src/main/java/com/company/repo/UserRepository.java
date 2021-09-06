package com.company.repo;

import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(String uuid);

    @Query("From User u where 1=2 or u.name in (?1) or u.surname in (?2) or u.email in (?3)")
    List<User> searchNameOrSurnameOrUsername(String name, String surname, String email);

}
