package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author sxpersxnic
 */
@Data
public class UserRequestDTO {
    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;
}
