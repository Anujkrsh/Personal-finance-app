package com.olivedevs.personalfinanceapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="Budget")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long userId;
    @Column(nullable = false)
    int amount;
    @Column
    String category;
    @Column(nullable = false)
    Date start_date;
    @Column(nullable = false)
    Date end_date;
}
