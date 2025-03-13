package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.controller.ProfileController;
import com.github.sxpersxnic.tbz.m320.repository.RoleRepository;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.repository.UserRepository;
import com.github.sxpersxnic.tbz.m320.controller.AuthController;
import com.github.sxpersxnic.tbz.m320.config.SecurityConfiguration;
import com.github.sxpersxnic.tbz.m320.controller.UserController;

import io.micrometer.common.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.*;

/// Service component for {@link User} entities.
/// @see User
/// @see CrudService
/// @see UserController
/// @author sxpersxnic
@Service
public class UserService implements CrudService<User, UUID> {

    /// The JPA repository for {@link User} entities.
    private final UserRepository userRepository;
    /// The JPA repository for {@link Role} entities.
    private final RoleRepository roleRepository;
    /// The Service for {@link Profile} entities.
    private final ProfileService profileService;

    /// @see PasswordEncoder
    /// @see SecurityConfiguration#passwordEncoder()
    private final PasswordEncoder passwordEncoder;

    /// Constructor for the {@link UserService}.
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ProfileService profileService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
    }

    /// Find all {@link User} entities.
    /// @return A list of all {@link User} entities.
    /// @see UserRepository#findAll()
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /// Find a {@link User} entity by its ID.
    /// @param id The ID of the {@link User} entity.
    /// @return The {@link User} entity with the given ID.
    /// @throws EntityNotFoundException If no {@link User} entity with the given ID exists.
    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /// Find a {@link User} entity by its email.
    ///
    /// This will be used at {@link AuthController#signIn}
    /// @param email The email of the {@link User} entity.
    /// @return The {@link User} entity with the given email.
    /// @throws EntityNotFoundException If no {@link User} entity with the given email exists.
    /// @see UserRepository#findUserByEmail(String)
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    /// Delete a {@link User} entity by its ID.
    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    /// Create a new {@link User} entity.
    ///
    /// The password will be encoded before saving the entity.
    /// @param user The {@link User} entity to create.
    /// @return The created {@link User} entity.
    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /// Create a new {@link User} entity.
    ///
    /// This method will also create a {@link Profile} entity.
    ///
    /// That's why there isn't a **create** method in the {@link ProfileController}.
    public User create(User newUser, String username) {
        Role defaultRole = roleRepository.findByName("USER").orElseThrow(EntityNotFoundException::new);
        Profile profile = new Profile(username);

        String password = newUser.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        newUser.getAssignedRoles().add(defaultRole);

        profile.setUser(newUser);
        newUser.getProfiles().add(profileService.create(profile));
        return userRepository.save(newUser);
    }

    /// Update a {@link User} entity.
    ///
    /// For security reasons, the password will be encoded before saving the entity.
    /// @param changing The {@link User} entity to update. Only the fields that are **not null** will be updated.
    /// @param id The ID of the {@link User} entity to update.
    /// @return The updated {@link User} entity.
    /// @throws EntityNotFoundException If no {@link User} entity with the given ID exists.
    @Override
    public User update(User changing, UUID id) {
        User existing = findById(id);
        merge(existing, changing);
        String password = existing.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        existing.setPassword(encodedPassword);
        return userRepository.save(existing);
    }

    /// Merge two {@link User} entities.
    ///
    /// Is used in the {@link #update} method.
    ///
    /// If the **changing** entity has a field that is not **null**, it will be updated in the **existing** entity.
    ///
    /// If the **changing** entity has a field that is **null**, it will be ignored.
    ///
    /// If the **changing** entity has a field that is **blank**, an {@link FailedValidationException} will be thrown.
    ///
    /// @param existing The existing {@link User} entity.
    /// @param changing The changing {@link User} entity.
    /// @throws FailedValidationException If a field is **blank**.
    ///
    /// @see FailedValidationException
    /// @see StringUtils#isNotBlank
    private void merge(User existing, User changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getEmail() != null) {
            if (StringUtils.isNotBlank(changing.getEmail())) {
                existing.setEmail(changing.getEmail());
            } else {
                errors.put("email", List.of("Email must not be blank!"));
            }
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
