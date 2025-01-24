package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import java.util.UUID;

/**
 * @param accessToken
 * @param userId
 *
 * @author sxpersxnic
 */
public record SignInResponseDTO(String accessToken, UUID userId, UUID profileId) {}
