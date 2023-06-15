package com.easy.bank.config;

import com.easy.bank.model.Customer;
import com.easy.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EasyBankPwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Customer customer = customerRepository.findByEmail(authentication.getName());
        if (customer != null) {
            if (passwordEncoder.matches(authentication.getCredentials().toString(), customer.getPwd())) {
                return new UsernamePasswordAuthenticationToken(authentication.getName(),
                        authentication.getCredentials().toString(),
                        customer.getAuthorities().stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                                .toList());
            } else {
                throw new BadCredentialsException("Invalid password.");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
