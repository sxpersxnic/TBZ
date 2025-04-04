package com.github.sxpersxnic.tbz.m320.util;


import com.github.sxpersxnic.tbz.m320.payload.dto.request.UserRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.UserResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class DataDTOUtil {

    public static UserResponseDTO getTestUserResponseDTO() {
        return getTestUserResponseDTOs().getFirst();
    }

    public static UserRequestDTO getTestUserRequestDTO() {
        UserRequestDTO signUpDTO = new UserRequestDTO();
        signUpDTO.setEmail("user1@foo.bar");
        // signUpDTO.setPassword("password1");
        return signUpDTO;
    }

    public static List<UserResponseDTO> getTestUserResponseDTOs() {
        List<UserResponseDTO> userList = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            UserResponseDTO user = new UserResponseDTO();
            user.setId(DataUtil.testUUID(i));
            user.setEmail("user" + i + "@foo.bar");
            user.setRoleIds(new ArrayList<>());
            user.getRoleIds().add(DataUtil.testUUID(3));
            userList.add(user);
        }
        return userList;
    }
}
