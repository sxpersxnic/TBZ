package com.m320.api.payload.mapper.auth;

import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.payload.dto.request.auth.SignUpRequestDTO;
import com.m320.api.payload.dto.response.auth.SignUpResponseDTO;

import java.time.LocalDateTime;

public class SignUpMapper {

    public static SignUpResponseDTO toDTO(User user, Profile profile) {
        SignUpResponseDTO dto = new SignUpResponseDTO();

        dto.setUserId(user.getId());
        dto.setProfileId(profile.getId());
        dto.setUsername(profile.getUsername());
        dto.setEmail(user.getEmail());
        dto.setProfilePicture(profile.getProfilePicture());

        return dto;
    }

    public static User fromDTO(SignUpRequestDTO dto) {
        Profile profile = new Profile();
        User user = new User();

        profile.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        profile.setCreatedAt(LocalDateTime.now());

        profile.setUser(user);
        user.getProfile().add(profile);

        return user;
    }
}
