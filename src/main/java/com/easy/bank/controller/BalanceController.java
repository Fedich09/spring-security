package com.easy.bank.controller;

import com.easy.bank.model.AccountTransactions;
import com.easy.bank.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository repository;

        @GetMapping("/myBalance")
        public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
            return repository.findByCustomerIdOrderByTransactionDtDesc(id);
        }
}
