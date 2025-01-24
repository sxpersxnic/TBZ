package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import lombok.Data;

import java.util.UUID;
import java.util.List;

@Data
public class ProfileResponseDTO {

    private UUID id;
    private UUID userId;
    private String username;
    private String profilePicture;

    private List<UUID> questionIds;
    private List<UUID> answerIds;

}
