package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.UserRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.UserResponseDTO;
import com.github.sxpersxnic.tbz.m320.model.Role;

import java.util.List;
import java.util.UUID;

/// Class to map User objects to UserResponseDTO objects and vice versa.
/// @see User
/// @see UserResponseDTO
/// @see UserRequestDTO
/// @author sxpersxnic
public class UserMapper {
    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());

        UUID profileId = user
                .getProfiles()
                .stream()
                .map(Profile::getId)
                .toList()
                .getFirst();
        dto.setProfileId(profileId);

        List<UUID> roleIds = user.getAssignedRoles()
                .stream()
                .map(Role::getId)
                .toList();
        dto.setRoleIds(roleIds);
        return dto;
    }

    public static User fromDTO(UserRequestDTO dto) {
        User user = new User();

//        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }
}
