package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.controller.ProfileController;
import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.repository.ProfileRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/// Service component for {@link Profile} entities.
/// @see Profile
/// @see CrudService
/// @see ProfileController
/// @author sxpersxnic
@Service
public class ProfileService implements CrudService<Profile, UUID> {
    /// The JPA repository for {@link Profile} entities.
    private final ProfileRepository profileRepository;

    /// Constructor for the {@link ProfileService}.
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /// Find all {@link Profile} entities.
    /// @return A list of all {@link Profile} entities.
    /// @see ProfileRepository#findAll()
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    /// Find a {@link Profile} entity by its ID.
    /// @param id The ID of the {@link Profile} entity.
    /// @return The {@link Profile} entity with the given ID.
    /// @throws EntityNotFoundException If no {@link Profile} entity with the given ID exists.
    @Override
    public Profile findById(UUID id) {
        return profileRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /// Delete a {@link Profile} entity by its ID.
    @Override
    public void delete(UUID id) {
        profileRepository.deleteById(id);
    }

    /// Create a new {@link Profile} entity.
    ///
    /// This method is only used in the {@link UserService#create} method and not in the {@link ProfileController}.
    /// @param profile The {@link Profile} entity to create.
    /// @return The created {@link Profile} entity.
    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    /// Update a {@link Profile} entity.
    /// @param changing The {@link Profile} entity to update. Only the fields that are **not null** will be updated.
    /// @param id The ID of the {@link Profile} entity to update.
    /// @return The updated {@link Profile} entity.
    /// @throws EntityNotFoundException If no {@link Profile} entity with the given ID exists.
    @Override
    public Profile update(Profile changing, UUID id) {
        Profile existing = findById(id);
        merge(changing, existing);
        return profileRepository.save(existing);
    }

    /// Merge two {@link Profile} entities.
    ///
    /// Is used in the {@link #update} method.
    ///
    /// If the **changing** entity has a field that is not **null**, it will be updated in the **existing** entity.
    ///
    /// If the **changing** entity has a field that is **null**, it will be ignored.
    ///
    /// If the **changing** entity has a field that is **blank**, an {@link FailedValidationException} will be thrown.
    ///
    /// @param existing The existing {@link Profile} entity.
    /// @param changing The changing {@link Profile} entity.
    /// @throws FailedValidationException If a field is **blank**.
    ///
    /// @see FailedValidationException
    /// @see StringUtils#isNotBlank
    private void merge(Profile changing, Profile existing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getUsername() != null) {
            if (StringUtils.isNotBlank(changing.getUsername())) {
                existing.setUsername(changing.getUsername());
            } else {
                errors.put("Username", List.of("Username must not be blank"));
            }
        }

        if (changing.getProfilePicture() != null) {
            if (StringUtils.isNotBlank(changing.getProfilePicture())) {
                existing.setProfilePicture(changing.getProfilePicture());
            }
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
