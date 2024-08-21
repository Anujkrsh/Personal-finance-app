package com.olivedevs.personalfinanceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionResponse {
    @JsonProperty("base")
    private String baseCurrency;

    @JsonProperty("to")
    private String toCurrency;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("converted")
    private Double convertedAmount;

    @JsonProperty("rate")
    private Double conversionRate;

    @JsonProperty("lastUpdate")
    private Long lastUpdate;
}
