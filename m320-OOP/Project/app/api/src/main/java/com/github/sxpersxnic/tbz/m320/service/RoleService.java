package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.repository.RoleRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Service
public class RoleService {

    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Role findById(UUID id) {
        return roleRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }

    public void deleteById(UUID id) {
        roleRepo.deleteById(id);
    }

    public Role update(Role changing, UUID id) {
        Role existing = findById(id);
        mergeRoles(existing, changing);
        return roleRepo.save(existing);
    }

    public Role create(Role role) {
        return roleRepo.save(role);
    }

    private void mergeRoles(Role existing, Role changing) {
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
