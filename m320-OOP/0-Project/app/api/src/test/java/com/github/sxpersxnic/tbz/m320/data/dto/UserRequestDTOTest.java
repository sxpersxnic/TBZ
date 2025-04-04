package com.github.sxpersxnic.tbz.m320.data.dto;

import com.github.sxpersxnic.tbz.m320.payload.dto.request.UserRequestDTO;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRequestDTOTest {

    @Test
    public void constructor_hasEmptyConstructor() {
        boolean hasEmptyConstructor = Arrays.stream(UserRequestDTO.class.getConstructors())
                .anyMatch(constructor -> constructor.getParameterCount() == 0);
        assertTrue(hasEmptyConstructor);
    }

    @Test
    public void passwordField_doesExist() {
        assertDoesNotThrow(() -> UserRequestDTO.class.getDeclaredField("password"));
    }
}
