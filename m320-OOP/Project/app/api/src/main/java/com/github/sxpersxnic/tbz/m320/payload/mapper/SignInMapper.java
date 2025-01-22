package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.SignInRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.SignInResponseDTO;

import java.util.UUID;

public class SignInMapper {

    public static User fromDTO(SignInRequestDTO dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public static SignInResponseDTO toDTO(String token, User user) {
        UUID profileId = user
                .getProfiles()
                .stream()
                .map(Profile::getId)
                .toList()
                .getFirst();
        return new SignInResponseDTO(token, user.getId(), profileId);
    }
}
