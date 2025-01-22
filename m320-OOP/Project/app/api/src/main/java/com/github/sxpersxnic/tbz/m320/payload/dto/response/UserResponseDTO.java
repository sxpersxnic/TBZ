package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
public class UserResponseDTO {

    private UUID id;

    @Email(message = "Invalid email!", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotBlank(message = "Email can not be empty!")
    private String email;
    private List<UUID> roleIds;

    public UUID getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public List<UUID> getRoleIds() {
        return roleIds;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleIds(List<UUID> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
