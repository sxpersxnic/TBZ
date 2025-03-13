package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;


/// Response DTO for role
/// @author sxpersxnic
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleResponseDTO extends ResponseDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    private List<UUID> assignedUserIds;
}
