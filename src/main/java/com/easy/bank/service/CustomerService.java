package com.easy.bank.service;

import com.easy.bank.model.Customer;
import com.easy.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownServiceException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long register(Customer customer) throws Exception {
        customer.setPwd(encoder.encode(customer.getPwd()));
        Customer saved = customerRepository.save(customer);
        if (saved.getId() == null) {
            throw new Exception(String
                    .format("Can't save customer with email - %s", customer.getEmail()));
        }
        return saved.getId();
    }
}
