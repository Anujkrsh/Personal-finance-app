package com.olivedevs.personalfinanceapp.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiConfig {

    @Value("${API_KEY}")
    private String apiKey;
}
