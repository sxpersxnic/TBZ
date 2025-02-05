package com.github.sxpersxnic.tbz.m320.data.mapper;

import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.payload.mapper.UserMapper;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.UserRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.UserResponseDTO;
import com.github.sxpersxnic.tbz.m320.util.DataDTOUtil;
import com.github.sxpersxnic.tbz.m320.util.DataUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {
    @Test
    public void fromDTO() {
        UserRequestDTO dto = DataDTOUtil.getTestUserRequestDTO();
        User user = UserMapper.fromDTO(dto);

        // assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getEmail(), user.getEmail());

    }

    @Test
    public void toDTO() {
        User user = DataUtil.getTestUser();
        UserResponseDTO dto = UserMapper.toDTO(user);

        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getEmail(), user.getEmail());

    }
}
