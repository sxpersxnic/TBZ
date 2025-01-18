package com.m320.api.payload.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProfileResponseDTO {
    private UUID id;
    private UUID userId;
    private String username;
    private String profilePicture;
    private LocalDateTime createdAt;

//    private Set<UUID> followerIds = new LinkedHashSet<>();
//    private Set<UUID> followingIds = new LinkedHashSet<>();
}
