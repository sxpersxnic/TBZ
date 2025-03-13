package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

/// Response DTO for user
/// @author sxpersxnic
@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponseDTO extends ResponseDTO {
    private UUID profileId;
    private List<UUID> roleIds;
    private String email;
}
