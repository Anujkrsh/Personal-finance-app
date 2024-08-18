package com.olivedevs.personalfinanceapp.repository;

import com.olivedevs.personalfinanceapp.model.Budget;
import com.olivedevs.personalfinanceapp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(Long id);
}
