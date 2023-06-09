package com.easy.bank.controller;

import com.easy.bank.model.Customer;
import com.easy.bank.repository.CustomerRepository;
import com.easy.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(String.format("Customer created, id - %s", customerService.register(customer)));
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return customerRepository.findByEmail(authentication.name());
    }
}
