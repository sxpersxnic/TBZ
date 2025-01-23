package com.github.sxpersxnic.tbz.m320.service;

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

/**
 * @author sxpersxnic
 */
@Service
public class ProfileService implements CrudService<Profile, UUID> {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    /**
     * @param id  Identifier of profile to be deleted.
     */
    @Override
    public void delete(UUID id) {
        profileRepository.deleteById(id);
    }

    public Profile findById(UUID id) {
        return profileRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile update(Profile changing, UUID id) {
        Profile existing = findById(id);
        merge(changing, existing);
        return profileRepository.save(existing);
    }

    /**
     * @param changing Changing profile, defining updating attributes
     * @param existing Existing profile, that will set the changing attributes
     */
    @Override
    public void merge(Profile changing, Profile existing) {
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
