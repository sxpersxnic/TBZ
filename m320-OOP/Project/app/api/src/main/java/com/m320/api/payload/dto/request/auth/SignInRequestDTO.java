package com.m320.api.payload.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDTO {

    @NotBlank(message = "Invalid Sign in!")
    private String email;

    @NotBlank(message = "Invalid Sign in!")
    private String password;
}
