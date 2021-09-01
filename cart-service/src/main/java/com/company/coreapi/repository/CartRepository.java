package com.company.coreapi.repository;

import com.company.coreapi.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CartRepository extends MongoRepository<Cart, UUID> {
}
