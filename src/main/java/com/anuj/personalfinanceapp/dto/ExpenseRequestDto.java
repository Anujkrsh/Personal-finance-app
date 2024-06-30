package com.anuj.personalfinanceapp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequestDto {
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("item")
    private String item;
    @JsonProperty("description")
    private String description;
}
