package com.m320.api.payload.dto.response.auth;

import lombok.Data;

import java.util.UUID;

@Data
public class SignInResponseDTO {
    private UUID id;
    private String username;
    private String token;
}
