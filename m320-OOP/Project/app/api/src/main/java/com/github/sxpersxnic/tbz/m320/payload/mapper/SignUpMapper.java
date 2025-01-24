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
        UUID profileId = user
                .getProfiles()
                .stream()
                .map(Profile::getId)
                .toList()
                .getFirst();
        return new SignUpResponseDTO(user.getId(), profileId, user.getEmail());
    }

}
