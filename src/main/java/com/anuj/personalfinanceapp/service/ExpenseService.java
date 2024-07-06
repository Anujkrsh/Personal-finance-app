package com.anuj.personalfinanceapp.service;

import com.anuj.personalfinanceapp.model.Expense;
import com.anuj.personalfinanceapp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService  {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findById(long id) {
        return expenseRepository.findById(id);
    }

    public void deleteById(int id) {
        expenseRepository.deleteById((long) id);
    }
}
