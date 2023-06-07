package com.easy.bank.config;

import com.easy.bank.model.Customer;
import com.easy.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class EasyBankUserDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = repository.findByEmail(email);
        User user = null;
        if (customer != null) {
            user = new User(customer.getEmail(), customer.getPwd(),
                    Collections.singletonList(new SimpleGrantedAuthority(customer.getRole())));
        } else {
            throw new UsernameNotFoundException(String.format("User with such email - \"%s\" not found.", email));
        }
        return user;
    }
}
