package com.m320.api.payload.dto.request.auth;

import com.m320.api.lib.validation.password.Password;
import com.m320.api.lib.validation.principles.Email;
import com.m320.api.lib.validation.principles.Username;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequestDTO {

    @Username(message = "Invalid username!")
    @NotBlank(message = "Username must not be blank!")
    private String username;

    @Email(message = "Invalid email!")
    @NotBlank(message = "Email must not be blank!")
    private String email;

    @Password
    @NotBlank(message = "Password must not be blank!")
    private String password;
}
