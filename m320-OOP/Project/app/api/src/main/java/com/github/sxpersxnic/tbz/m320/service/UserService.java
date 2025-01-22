package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.repository.RoleRepository;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(User user, UUID id) {
        User existing = this.findById(id);
        mergeUser(existing, user);
        String password = existing.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        existing.setPassword(encodedPassword);
        return userRepository.save(existing);
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public User create(User newUser) {
        Role defaultRole = roleRepository.findByName("USER");
        String password = newUser.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        newUser.getAssignedRoles().add(defaultRole);
        return userRepository.save(newUser);
    }
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
    private void mergeUser(User existing, User changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getEmail() != null) {
            if (StringUtils.isNotBlank(changing.getEmail())) {
                existing.setEmail(changing.getEmail());
            } else {
                errors.put("email", List.of("Email can not be empty!"));
            }
        }
        if (changing.getPassword() != null) {
            if (StringUtils.isNotBlank(changing.getPassword())) {
                existing.setPassword(changing.getPassword());
            } else {
                errors.put("password", List.of("Password can not be empty!"));
            }
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
