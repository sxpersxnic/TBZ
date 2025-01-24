package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Data
public class QuestionResponseDTO {

    private UUID id;
    private String content;
    private String description;
    private UUID ownerId;
    private List<UUID> answerIds;
    private List<UUID> optionIds;
    private LocalDateTime createdAt;

}
