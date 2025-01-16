package com.m320.api.payload.dto.response.auth;

import lombok.Data;

import java.util.UUID;
@Data
public class SignUpResponseDTO {
    private UUID id;
    private UUID profileId;
    private String username;
    private static final String profilePicture = "/default.png";
}
