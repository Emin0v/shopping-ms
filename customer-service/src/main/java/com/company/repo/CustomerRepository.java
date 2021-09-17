package com.company.repo;

import com.company.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> getByUserUuid(String uuid);

    @Query("select c.balance from Customer c where c.userUuid=:userUuid")
    BigDecimal getBalance(@Param("userUuid") String userUuid);

    @Modifying
    @Query("UPDATE Customer c set c.balance=:totalAmount where c.id=:id")
    void updateBalance(@Param("id") Long id , @Param("totalAmount") BigDecimal totalAmount);
}
