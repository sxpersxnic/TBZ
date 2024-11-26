package m320.projekt.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import m320.projekt.lib.exceptions.FailedValidationException;
import m320.projekt.lib.interfaces.CrudService;
import m320.projekt.model.Role;
import m320.projekt.repository.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements CrudService<Role, Integer> {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Role findByIdForUpdate(Integer id) {
        return roleRepository.findByIdForUpdate(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Role update(Role changing, Integer id) {
        Role existing = findByIdForUpdate(id);
        merge(existing, changing);
        return roleRepository.save(existing);
    }

    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public void merge(Role existing, Role changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getName() != null) {
            if (!StringUtils.isNotBlank(changing.getName())) {
                errors.put("name", List.of("Name can not be empty!"));
            } else if (existsByName(changing.getName())) {
                errors.put("name", List.of("Name already exists!"));
            } else if (changing.getName().equals(existing.getName())) {
                errors.put("name", List.of("Please choose a different name than the current!"));
            } else {
                existing.setName(changing.getName());
            }
        }

        if (changing.getUsers() != null) {
            existing.setUsers(changing.getUsers());
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
