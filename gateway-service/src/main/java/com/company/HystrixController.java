package com.company;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class HystrixController {

    @RequestMapping("/users")
    public String accountFallback(){
        return "Customer Service is not available.";
    }

    @RequestMapping("/products")
    public String productFallback(){
        return "Product Service is not available.";
    }

    @RequestMapping("/auth")
    public String authFallback(){
        return "Auth Service is not available.";
    }

    @RequestMapping("/order")
    public String orderFallback(){
        return "Auth Service is not available.";
    }
}
