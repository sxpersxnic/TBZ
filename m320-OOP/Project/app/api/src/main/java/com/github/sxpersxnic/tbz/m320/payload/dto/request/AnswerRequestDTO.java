package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AnswerRequestDTO {
 private UUID profileId;
 private UUID optionId;
}
