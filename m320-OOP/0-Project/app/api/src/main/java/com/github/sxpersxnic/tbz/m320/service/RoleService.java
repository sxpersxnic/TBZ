package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.repository.RoleRepository;
import com.github.sxpersxnic.tbz.m320.controller.RoleController;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/// Service component for {@link Role} entities.
/// @see Role
/// @see CrudService
/// @see RoleController
/// @author sxpersxnic
@Service
public class RoleService implements CrudService<Role, UUID> {

    /// The JPA repository for {@link Role} entities.
    private final RoleRepository roleRepository;

    /// Constructor for {@link RoleService}.
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /// Find all {@link Role} entities.
    /// @return A list of all {@link Role} entities.
    /// @see RoleRepository#findAll()
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /// Find a {@link Role} entity by its ID.
    /// @param id The ID of the {@link Role} entity.
    /// @return The {@link Role} entity with the given ID.
    /// @throws EntityNotFoundException If no {@link Role} entity with the given ID exists.
    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /// Find a {@link Role} entity by its name.
    /// @param name The name of the {@link Role} entity.
    /// @return The {@link Role} entity with the given name.
    /// @throws EntityNotFoundException If no {@link Role} entity with the given name exists.
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    /// Delete a {@link Role} entity by its ID.
    @Override
    public void delete(UUID id) {
        roleRepository.deleteById(id);
    }

    /// Update a {@link Role} entity.
    ///
    /// @param changing The {@link Role} entity to update. Only the fields that are **not null** will be updated.
    /// @param id The ID of the {@link Role} entity to update.
    /// @return The updated {@link Role} entity.
    /// @throws EntityNotFoundException If no {@link Role} entity with the given ID exists.
    @Override
    public Role update(Role changing, UUID id) {
        Role existing = findById(id);
        merge(existing, changing);
        return roleRepository.save(existing);
    }

    /// Create a new {@link Role} entity.
    /// @param role The {@link Role} entity to create.
    /// @return The created {@link Role} entity.
    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }


    /// Merge two {@link Role} entities.
    ///
    /// Is used in the {@link #update} method.
    ///
    /// If the **changing** entity has a field that is not **null**, it will be updated in the **existing** entity.
    ///
    /// If the **changing** entity has a field that is **null**, it will be ignored.
    ///
    /// If the **changing** entity has a field that is **blank**, an {@link FailedValidationException} will be thrown.
    ///
    /// @param existing The existing {@link Role} entity.
    /// @param changing The changing {@link Role} entity.
    /// @throws FailedValidationException If a field is **blank**.
    ///
    /// @see FailedValidationException
    /// @see StringUtils#isNotBlank
    private void merge(Role existing, Role changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getName() != null) {
            if (StringUtils.isNotBlank(changing.getName())) {
                existing.setName(changing.getName());
            } else {
                errors.put("name", List.of("Name can not be empty!"));
            }
        }

        if (changing.getAssignedUsers() != null) {
            existing.setAssignedUsers(changing.getAssignedUsers());
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
