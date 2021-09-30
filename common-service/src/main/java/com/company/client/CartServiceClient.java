package com.company.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("cart-service")
public interface CartServiceClient {

    @PostMapping("/api/cart/createCart/{id}")
    ResponseEntity<HttpStatus> createCart(@PathVariable("id") String id);

}
