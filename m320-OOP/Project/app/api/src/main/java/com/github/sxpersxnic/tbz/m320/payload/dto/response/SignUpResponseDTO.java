package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import java.util.UUID;

/**
 * @author sxpersxnic
 * @param userId
 * @param email
 */
public record SignUpResponseDTO(UUID userId, String email) {}
