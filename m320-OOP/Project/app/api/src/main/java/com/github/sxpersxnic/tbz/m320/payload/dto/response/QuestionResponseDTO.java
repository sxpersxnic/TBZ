package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


/// Response DTO for question
/// @author sxpersxnic
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionResponseDTO extends ResponseDTO {
    private String content;
    private String description;
    private UUID profileId;
    private String username;
    private String profilePicture;
    private List<OptionResponseDTO> options;
    private ZonedDateTime createdAt;
    private int totalAnswerCount;

}
