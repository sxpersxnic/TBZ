package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author sxpersxnic
 */
@Data
public class SignUpRequestDTO {

    @NotBlank(message = "Username must not be empty")
    @Size(min = 3, max = 255, message = "Length must be between 3 and 255 characters")
    private String username;

    @NotBlank(message = "Email must not be empty")
    @Email
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, max = 255, message = "Length must be between 8 and 255 characters")
    private String password;
}
