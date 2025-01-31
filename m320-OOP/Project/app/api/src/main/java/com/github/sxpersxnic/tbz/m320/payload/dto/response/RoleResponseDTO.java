package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


/// Response DTO for role
/// @author sxpersxnic
@Data
public class RoleResponseDTO {

    private String id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    private List<String> assignedUserIds;
}
