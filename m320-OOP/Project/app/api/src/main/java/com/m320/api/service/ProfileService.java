package com.m320.api.service;

import com.m320.api.lib.exceptions.ExceptionMessages;
import com.m320.api.lib.exceptions.FailedValidationException;
import com.m320.api.lib.interfaces.CrudService;
import com.m320.api.model.Profile;
import com.m320.api.payload.dto.request.ProfileRequestDTO;
import com.m320.api.payload.dto.response.ProfileResponseDTO;
import com.m320.api.payload.mapper.ProfileMapper;
import com.m320.api.repository.ProfileRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileService implements CrudService<Profile, UUID, ProfileRequestDTO, ProfileResponseDTO> {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileResponseDTO findById(UUID id) {
        Profile profile = profileRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return ProfileMapper.toDTO(profile);
    }

    @Transactional
    public Profile findByIdForUpdate(UUID id) {
        return profileRepository.findByIdForUpdate(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ProfileResponseDTO> findAll() {

        List<Profile> profiles = profileRepository.findAll();

        return profiles.stream().map(ProfileMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProfileResponseDTO update(ProfileRequestDTO dto, UUID id) {
        Profile changing = ProfileMapper.fromDTO(dto);
        Profile existing = findByIdForUpdate(id);

        merge(existing, changing);

        Profile saved = profileRepository.save(existing);

        return ProfileMapper.toDTO(saved);
    }

    @Override
    public void delete(UUID id) {
        profileRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void merge(Profile existing, Profile changing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getUsername() != null) {
            if (StringUtils.isNotBlank(changing.getUsername())) {
                existing.setUsername(changing.getUsername());
            } else {
                errors.put("Username", List.of(ExceptionMessages.getNotBlankMessage("Username")));
            }
        }

        if (changing.getProfilePicture() != null) {
            if (StringUtils.isNotBlank(changing.getProfilePicture())) {
                existing.setProfilePicture(changing.getProfilePicture());
            } else {
                existing.setProfilePicture("/default.png");
            }
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }

//    public void followProfile(UUID followerId, UUID followeeId) {
//        Profile follower = profileRepository.findById(followerId).orElseThrow(EntityNotFoundException::new);
//        Profile followee = profileRepository.findById(followeeId).orElseThrow(EntityNotFoundException::new);
//
//        follower.getFollowing().add(followee);
//        followee.getFollowers().add(follower);
//
//        profileRepository.save(follower);
//        profileRepository.save(followee);
//    }

//    public void unfollowProfile(UUID followerId, UUID followeeId) {
//        Profile follower = profileRepository.findById(followerId).orElseThrow(EntityNotFoundException::new);
//        Profile followee = profileRepository.findById(followeeId).orElseThrow(EntityNotFoundException::new);
//
//        follower.getFollowing().remove(followee);
//        followee.getFollowers().remove(follower);
//
//        profileRepository.save(follower);
//        profileRepository.save(followee);
//    }
}
