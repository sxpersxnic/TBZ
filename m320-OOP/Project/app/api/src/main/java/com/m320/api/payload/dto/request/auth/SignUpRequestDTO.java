package com.m320.api.payload.dto.request.auth;

import com.m320.api.lib.validation.password.ConfirmPassword;
import com.m320.api.lib.validation.password.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ConfirmPassword(
        password = "password",
        confirmPassword = "confirmPassword"
)
public class SignUpRequestDTO {

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @Email(message = "Invalid email!")
    @NotBlank(message = "Email must not be blank!")
    private String email;

    @Password
    @NotBlank(message = "Password must not be blank!")
    private String password;

    private String confirmPassword;
}
