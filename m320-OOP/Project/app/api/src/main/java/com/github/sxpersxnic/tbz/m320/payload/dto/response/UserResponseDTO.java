package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import lombok.Data;
import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Data
public class UserResponseDTO {
    private UUID id;
    private String email;
    private List<UUID> roleIds;
    private UUID profileId;
}
