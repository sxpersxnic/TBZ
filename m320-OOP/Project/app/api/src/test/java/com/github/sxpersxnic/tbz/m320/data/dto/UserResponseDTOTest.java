package com.github.sxpersxnic.tbz.m320.data.dto;

import com.github.sxpersxnic.tbz.m320.payload.dto.response.UserResponseDTO;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserResponseDTOTest {

    @Test
    public void constructor_hasEmptyConstructor() {
        boolean hasEmptyConstructor = Arrays.stream(UserResponseDTO.class.getConstructors())
                .anyMatch(constructor -> constructor.getParameterCount() == 0);
        assertTrue(hasEmptyConstructor);
    }

    @Test
    public void idField_doesExist() {
        assertDoesNotThrow(() -> UserResponseDTO.class.getDeclaredField("id"));
    }

    @Test
    public void emailField_doesExist() {
        assertDoesNotThrow(() -> UserResponseDTO.class.getDeclaredField("email"));
    }

    @Test
    public void passwordField_doesNotExist() {
        assertThrows(NoSuchFieldException.class, () -> UserResponseDTO.class.getDeclaredField("password"));
    }
}
