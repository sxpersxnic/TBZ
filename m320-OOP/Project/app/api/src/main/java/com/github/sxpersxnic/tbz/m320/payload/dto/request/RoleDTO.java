package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */

@Data
public class RoleDTO {

    private UUID id;

    @NotBlank(message = "Name can not be empty!")
    private String name;

    private List<UUID> assignedUserIds;
}
