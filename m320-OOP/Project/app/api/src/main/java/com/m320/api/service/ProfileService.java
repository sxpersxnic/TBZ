package com.m320.api.service;

import com.m320.api.lib.exceptions.ExceptionMessages;
import com.m320.api.lib.exceptions.FailedValidationException;
import com.m320.api.lib.interfaces.CrudService;
import com.m320.api.model.Profile;
import com.m320.api.repository.ProfileRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProfileService implements CrudService<Profile, UUID> {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile findById(UUID uuid) {
        return profileRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Profile findByIdForUpdate(UUID uuid) {
        return profileRepository.findByIdForUpdate(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public Profile update(Profile changing, UUID uuid) {
        Profile existing = findByIdForUpdate(uuid);
        merge(changing, existing);
        return profileRepository.save(existing);
    }

    @Override
    public void delete(UUID uuid) {
        profileRepository.deleteById(uuid);
    }

    @Override
    public void merge(Profile existing, Profile changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getUsername() != null) {
            if (StringUtils.isNotBlank(changing.getUsername())) {
                existing.setUsername(changing.getUsername());
            } else {
                errors.put("username", List.of(ExceptionMessages.getNotBlankMessage("Username")));
            }
        }

        if (errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
