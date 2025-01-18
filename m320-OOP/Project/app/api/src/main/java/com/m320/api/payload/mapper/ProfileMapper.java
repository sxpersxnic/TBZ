package com.m320.api.payload.mapper;

import com.m320.api.model.Profile;
import com.m320.api.payload.dto.request.ProfileRequestDTO;
import com.m320.api.payload.dto.response.ProfileResponseDTO;

import java.util.stream.Collectors;

public class ProfileMapper {

    public static Profile fromDTO(ProfileRequestDTO dto) {
        Profile profile = new Profile();
        profile.setUsername(dto.getUsername());
        profile.setProfilePicture(dto.getProfilePicture());
        return profile;
    }

    public static ProfileResponseDTO toDTO(Profile src) {
        ProfileResponseDTO dto = new ProfileResponseDTO();

        dto.setId(src.getId());
        dto.setUsername(src.getUsername());
        dto.setProfilePicture(src.getProfilePicture());

        dto.setUserId(src.getUser().getId());

        dto.setCreatedAt(src.getCreatedAt());

//        dto.setFollowerIds(
//                src.getFollowers()
//                        .stream()
//                        .map(follower -> follower.getId())
//                        .collect(Collectors.toSet())
//        );

//        dto.setFollowingIds(
//                src.getFollowing()
//                        .stream()
//                        .map(followee -> followee.getId())
//                        .collect(Collectors.toSet())
//        );

        return dto;
    }
}
