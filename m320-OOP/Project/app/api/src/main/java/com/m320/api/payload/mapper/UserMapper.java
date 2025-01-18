package com.m320.api.payload.mapper;

import com.m320.api.model.User;
import com.m320.api.payload.dto.request.UserRequestDTO;
import com.m320.api.payload.dto.response.UserResponseDTO;

public class UserMapper {

    public static User fromDTO(UserRequestDTO dto) {
        User user = new User();

        user.setEmail(dto.getEmail());

        return user;
    }

    public static UserResponseDTO toDTO(User src) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(src.getId());
        dto.setProfileId(src.getProfile().getFirst().getId());
        dto.setEmail(src.getEmail());

        return dto;
    }
}
