package com.m320.api.payload.mapper.auth;

import com.m320.api.lib.interfaces.Mapper;
import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.payload.dto.request.auth.SignInRequestDTO;
import com.m320.api.payload.dto.response.auth.SignInResponseDTO;

@Mapper
public class SignInMapper {

    public static SignInResponseDTO toDTO(Profile src, String token) {
        SignInResponseDTO dto = new SignInResponseDTO();

        dto.setId(src.getId());
        dto.setUsername(src.getUsername());
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
