package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author sxpersxnic
 */
@Data
public class UserRequestDTO {
    @Size(min = 8, message = "Password needs to be at least 8 characters long")
    public String password;

    @Email(message = "Invalid email!", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
}
