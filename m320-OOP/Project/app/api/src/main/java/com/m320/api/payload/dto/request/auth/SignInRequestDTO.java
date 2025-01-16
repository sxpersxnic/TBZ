package com.m320.api.payload.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequestDTO {

    @NotBlank(message = "Email must not be blank!")
    private String email;

    @NotBlank(message = "Password must not be blank!")
    private String password;
}
