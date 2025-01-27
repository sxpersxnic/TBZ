package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
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
 * Service component for {@link Role}
 *
 * @author sxpersxnic
 */
@Service
public class RoleService implements CrudService<Role, UUID> {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void delete(UUID id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role changing, UUID id) {
        Role existing = findById(id);
        merge(existing, changing);
        return roleRepository.save(existing);
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void merge(Role existing, Role changing) {
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
