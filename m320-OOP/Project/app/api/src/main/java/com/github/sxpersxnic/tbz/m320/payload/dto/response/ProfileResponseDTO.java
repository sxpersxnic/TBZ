package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;
import java.util.List;

/**
 * @author sxpersxnic
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProfileResponseDTO extends ResponseDTO {
    private UUID userId;
    private String username;
    private String profilePicture;
    private List<UUID> questionIds;
    private List<UUID> answerIds;
}
