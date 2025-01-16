package com.m320.api.payload.mapper.auth;

import com.m320.api.lib.utils.Account;
import com.m320.api.lib.interfaces.Mapper;
import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.payload.dto.request.auth.SignUpRequestDTO;
import com.m320.api.payload.dto.response.auth.SignUpResponseDTO;

import java.time.LocalDateTime;

@Mapper
public class SignUpMapper {

    public static SignUpResponseDTO toDTO(Profile src) {
        SignUpResponseDTO dto = new SignUpResponseDTO();

        dto.setId(src.getUser().getId());
        dto.setProfileId(src.getId());
        dto.setUsername(src.getUsername());
        return dto;
    }

    public static Account fromDTO(SignUpRequestDTO dto) {
        Profile profile = new Profile();
        User user = new User();
        Account account = new Account();

        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setProfile(profile);

        profile.setUsername(dto.getUsername());
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUser(user);

        account.setProfile(profile);
        account.setUser(user);

        return account;
    }
}
