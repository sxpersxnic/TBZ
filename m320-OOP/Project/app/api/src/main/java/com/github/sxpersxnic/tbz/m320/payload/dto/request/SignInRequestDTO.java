package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Objects;

/**
 * @author sxpersxnic
 */

@Data
public class SignInRequestDTO {

    @NotBlank(message = "Email must not be empty")
    private String email;

    @NotBlank(message = "Password must not be empty")
    private String password;

    public SignInRequestDTO() {
    }
}
