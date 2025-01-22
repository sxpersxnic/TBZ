package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
public class RoleDTO {

    private UUID id;

    @NotBlank(message = "Name can not be empty!")
    private String name;
    private List<UUID> assignedUserIds;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<UUID> getAssignedUserIds() {
        return assignedUserIds;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAssignedUserIds(List<UUID> assignedUserIds) {
        this.assignedUserIds = assignedUserIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(id, roleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
