package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.ProfileRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.ProfileResponseDTO;

/**
 * @author sxpersxnic
 */
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
        dto.setUserId(src.getUser().getId());
        dto.setUsername(src.getUsername());
        dto.setProfilePicture(src.getProfilePicture());

        return dto;
    }
}
