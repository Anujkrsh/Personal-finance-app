package com.olivedevs.personalfinanceapp.repository;

import com.olivedevs.personalfinanceapp.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
