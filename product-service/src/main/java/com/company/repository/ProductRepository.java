package com.company.repository;

import com.company.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByUuid(String uuid);

    List<Product> findByUuidIn(List<String> productUuids);


}
