package com.github.sxpersxnic.tbz.m320.payload.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


/// DTO for role
/// @author sxpersxnic
@Data
public class RoleDTO {

    private String id;
    @NotBlank(message = "Name can not be empty!")
    private String name;

    private List<String> assignedUserIds;
}
