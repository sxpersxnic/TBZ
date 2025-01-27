package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.SignUpRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.SignUpResponseDTO;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author sxpersxnic
 */
public class SignUpMapper {

    public static User fromDTO(SignUpRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static SignUpResponseDTO toDTO(User user) {
        SignUpResponseDTO dto = new SignUpResponseDTO();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());

        UUID profileId = user
                .getProfiles()
                .stream()
                .map(Profile::getId)
                .toList()
                .getFirst();
        dto.setProfileId(profileId);

        return dto;
    }

}
