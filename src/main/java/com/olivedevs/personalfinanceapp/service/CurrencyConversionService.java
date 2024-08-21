package com.olivedevs.personalfinanceapp.service;
import com.olivedevs.personalfinanceapp.config.ApiConfig;
import com.olivedevs.personalfinanceapp.model.CurrencyConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CurrencyConversionService {

    private final WebClient webClient;
    private final ApiConfig apiConfig;

    @Autowired
    public CurrencyConversionService(WebClient.Builder webClientBuilder, ApiConfig apiConfig) {
        this.webClient = webClientBuilder.baseUrl("https://anyapi.io/api/v1/exchange/convert").build();
        this.apiConfig = apiConfig;
    }

    public Mono<Double> convertCurrency(String fromCurrency, String toCurrency, Double amount) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("base", fromCurrency)
                        .queryParam("to", toCurrency)
                        .queryParam("amount", amount)
                        .queryParam("apiKey", apiConfig.getApiKey())
                        .build())
                .retrieve()
                .bodyToMono(CurrencyConversionResponse.class)
                .map(CurrencyConversionResponse::getConvertedAmount);
    }
}
