package com.example.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/authServiceFallBack")
    public String authServiceFallBackMethod() {
        return "Auth Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/cartServiceFallBack")
    public String cartServiceFallBackMethod() {
        return "Cart Service is taking longer than Expected." +
                " Please try again later";
    }
}
