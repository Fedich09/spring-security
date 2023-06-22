package com.easy.bank.controller;

import com.easy.bank.model.Loans;
import com.easy.bank.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository repository;
    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoansDetails(@RequestParam int id) {
        return repository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
