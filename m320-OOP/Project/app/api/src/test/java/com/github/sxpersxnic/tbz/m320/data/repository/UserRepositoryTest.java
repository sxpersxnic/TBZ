package com.github.sxpersxnic.tbz.m320.data.repository;

import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class UserRepositoryTest {

    @Test
    public void repository_isInterface() {
        assertTrue(UserRepository.class.isInterface());
    }

    @Test
    public void repository_extendsJPARepository() {
        assertTrue(Arrays.asList(UserRepository.class.getInterfaces()).contains(JpaRepository.class));
    }

    @Test
    public void repositoryEntity_isUser() {
        assertTrue(Arrays.stream(UserRepository.class.getGenericInterfaces())
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> ((ParameterizedType) t))
                .anyMatch(t -> t.getRawType().equals(JpaRepository.class) && t.getActualTypeArguments()[0].equals(User.class)));
    }

    @Test
    public void repositoryId_isUUID() {
        assertTrue(Arrays.stream(UserRepository.class.getGenericInterfaces())
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> ((ParameterizedType) t))
                .anyMatch(t -> t.getRawType().equals(JpaRepository.class) && t.getActualTypeArguments()[1].equals(UUID.class)));
    }

    @Test
    public void repositoryFindUserByEmail_exists() {
        assertDoesNotThrow(() -> UserRepository.class.getDeclaredMethod("findUserByEmail", String.class));
    }

    @Test
    public void repositoryFindUserByEmail_returnsOptionalUser() {
        try {
            assertEquals(Optional.class, UserRepository.class.getDeclaredMethod("findUserByEmail", String.class).getReturnType());
        } catch (NoSuchMethodException e) {
            fail();
        }
    }

}
