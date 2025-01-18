package com.m320.api.payload.mapper.auth;

import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.payload.dto.response.auth.SignInResponseDTO;

public class SignInMapper {

    public static SignInResponseDTO toDTO(User user, Profile profile, String token) {
        SignInResponseDTO dto = new SignInResponseDTO();

        dto.setUserId(user.getId());
        dto.setProfileId(profile.getId());
        dto.setUsername(profile.getUsername());
        dto.setEmail(user.getEmail());
        dto.setToken(token);

        return dto;
    }
}
