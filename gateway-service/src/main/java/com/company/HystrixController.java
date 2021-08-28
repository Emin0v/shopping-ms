package com.company;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class HystrixController {

    @GetMapping("/users")
    public String accountFallback(){
        return "Customer Service is not available.";
    }

    @GetMapping("/products")
    public String ticketFallback(){
        return "Product Service is not available.";
    }

    @GetMapping("/auth")
    public String authFallback(){
        return "Auth Service is not available.";
    }
}
