package com.company.repo;

import com.company.dto.CustomerResDto;
import com.company.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("From Customer c where 1=2 or c.name in (?1) or c.surname in (?2) or c.username in (?3)")
    List<Customer> searchNameOrSurnameOrUsername(String name,String surname,String username);

}
