package com.anuj.personalfinanceapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenPayload {
    private String sub;
    private String user_id;
    private String iat;
    private String exp;
}
