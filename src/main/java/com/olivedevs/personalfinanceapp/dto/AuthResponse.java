package com.olivedevs.personalfinanceapp.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String access_Token;

    public AuthResponse(String message) {
        this.access_Token = message;
    }
}

