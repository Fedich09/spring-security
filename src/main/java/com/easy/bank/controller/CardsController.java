package com.easy.bank.controller;

import com.easy.bank.model.Cards;
import com.easy.bank.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository repository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam int id) {
        return repository.findByCustomerId(id);
    }
}
