package com.olivedevs.personalfinanceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveIncomeDTO {
    private Double amount;
    private String source;
    private LocalDate date;
    private String description;
}
