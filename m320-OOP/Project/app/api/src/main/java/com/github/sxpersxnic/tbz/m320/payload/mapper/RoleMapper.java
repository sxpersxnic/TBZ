package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.payload.dto.RoleDTO;

import java.util.List;
import java.util.UUID;


/// Class to map Role objects to RoleDTO objects and vice versa.
/// @see Role
/// @see RoleDTO
/// @author sxpersxnic
public class RoleMapper {
    public static RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();

        dto.setId(role.getId().toString());
        dto.setName(role.getName());

        List<String> userIds = role.getAssignedUsers()
                .stream()
                .map(user -> user.getId().toString())
                .toList();
        dto.setAssignedUserIds(userIds);
        return dto;
    }
    public static Role fromDTO(RoleDTO dto) {
        Role role = new Role();

        UUID id = UUID.fromString(dto.getId());
        role.setId(id);
        role.setName(dto.getName());
        return role;
    }
}
