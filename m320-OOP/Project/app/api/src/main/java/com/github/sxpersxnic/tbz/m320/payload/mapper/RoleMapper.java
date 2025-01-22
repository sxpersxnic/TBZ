package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.RoleDTO;
import com.github.sxpersxnic.tbz.m320.model.User;

import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
public class RoleMapper {
    public static RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();

        dto.setId(role.getId());
        dto.setName(role.getName());

        List<UUID> userIds = role.getAssignedUsers()
                .stream()
                .map(User::getId)
                .toList();
        dto.setAssignedUserIds(userIds);
        return dto;
    }
    public static Role fromDTO(RoleDTO dto) {
        Role role = new Role();

        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }
}
