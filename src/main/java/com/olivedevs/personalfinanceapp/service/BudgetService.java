package com.olivedevs.personalfinanceapp.service;

import com.olivedevs.personalfinanceapp.model.Budget;
import com.olivedevs.personalfinanceapp.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }
    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }
}
