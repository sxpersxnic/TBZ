package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerResponseDTO extends ResponseDTO {
    private UUID optionId;
    private UUID profileId;
    private String username;
    private String profilePicture;
    private ZonedDateTime createdAt;
}
