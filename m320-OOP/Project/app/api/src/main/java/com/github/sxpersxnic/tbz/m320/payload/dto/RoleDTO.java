package com.github.sxpersxnic.tbz.m320.payload.dto;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends ResponseDTO {

    @NotBlank(message = "Name can not be empty!")
    private String name;

    private List<UUID> assignedUserIds;
}
