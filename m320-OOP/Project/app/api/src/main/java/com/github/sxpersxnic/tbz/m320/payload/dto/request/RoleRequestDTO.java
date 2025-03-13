package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequestDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;
}
