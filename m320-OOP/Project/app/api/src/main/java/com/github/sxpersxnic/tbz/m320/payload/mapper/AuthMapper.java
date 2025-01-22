package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.AuthRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.SignUpResponseDTO;

/**
 * @author sxpersxnic
 */
public class AuthMapper {

    public static User fromDTO(AuthRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static SignUpResponseDTO toDTO(User source) {
        return new SignUpResponseDTO(source.getId(), source.getEmail());
    }

}
