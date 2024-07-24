package com.olivedevs.personalfinanceapp.repository;

import com.olivedevs.personalfinanceapp.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    public Date findByDate(Date date);

    void delete(Long id);
}
