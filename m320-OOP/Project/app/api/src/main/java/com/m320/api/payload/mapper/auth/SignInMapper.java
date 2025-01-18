package com.m320.api.payload.mapper.auth;

import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.payload.dto.request.auth.SignInRequestDTO;
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

    public static User fromDTO(SignInRequestDTO dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
}
