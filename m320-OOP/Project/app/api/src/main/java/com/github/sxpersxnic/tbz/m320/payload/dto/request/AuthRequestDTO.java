package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * @author sxpersxnic
 */
public class AuthRequestDTO {

    @NotBlank(message = "email must not be empty")
    @Email
    private String email;

    @NotBlank(message = "password must not be empty")
    @Size(min = 8, max = 255, message = "length must be between 8 and 255")
    private String password;

    public AuthRequestDTO() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        AuthRequestDTO that = (AuthRequestDTO) obj;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
