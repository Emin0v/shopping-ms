package com.company.repo;

import com.company.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface CartRepository extends JpaRepository<Cart, String> {



}
