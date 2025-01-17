package com.m320.api.payload.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class QuestionResponseDTO {
    private UUID id;
    private UUID user_id;
    private String question;
    private String description;
}
