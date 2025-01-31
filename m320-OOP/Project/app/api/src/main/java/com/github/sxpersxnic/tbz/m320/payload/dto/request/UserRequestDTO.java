package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author sxpersxnic
 */
@Data
public class UserRequestDTO {
//    @NotBlank(message = "Password must not be blank")
//    @Size(min = 8, message = "Password needs to be at least 8 characters long")
//    public String password;

    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;
}
