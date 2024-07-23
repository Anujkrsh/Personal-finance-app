package com.olivedevs.personalfinanceapp.service;


import com.olivedevs.personalfinanceapp.model.Expense;
import com.olivedevs.personalfinanceapp.model.Income;
import com.olivedevs.personalfinanceapp.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;


    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> findAll() {
        return incomeRepository.findAll();
    }
    public Optional<Income> findById(long id) {
        return incomeRepository.findById(id);
    }

    public Optional<Income> findByUserId(long userId) {
        return incomeRepository.findById(userId);
    }

    public void delete(int id) {
        incomeRepository.delete(id);
    }

}
