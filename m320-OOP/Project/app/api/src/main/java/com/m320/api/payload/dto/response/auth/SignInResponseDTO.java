package com.m320.api.payload.dto.response.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDTO {
    private String id;
    private String username;
    private String token;
}
