package com.anuj.personalfinanceapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String date;

    @Column(nullable = true)
    private String description;
}
