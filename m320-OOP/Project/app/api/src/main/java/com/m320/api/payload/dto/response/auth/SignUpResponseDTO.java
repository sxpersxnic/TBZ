package com.m320.api.payload.dto.response.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SignUpResponseDTO {
    private UUID id;
    private String username;
}
