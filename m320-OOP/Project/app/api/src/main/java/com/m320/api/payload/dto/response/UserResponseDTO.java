package com.m320.api.payload.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private UUID profileId;
    private String email;
}
