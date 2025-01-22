package com.github.sxpersxnic.tbz.m320.data.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.repository.UserRepository;
import com.github.sxpersxnic.tbz.m320.service.UserService;
import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.repository.RoleRepository;
import com.github.sxpersxnic.tbz.m320.util.DataUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void checkFindAll_whenUsersExist_thenUsersAreReturned() {
        List<User> expectedUsers = DataUtil.getTestUsers();
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findAll();

        assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            User expectedUser = expectedUsers.get(i);
            User actualUser = actualUsers.get(i);

            assertEquals(expectedUser.getId(), actualUser.getId());
            assertEquals(expectedUser.getEmail(), actualUser.getEmail());
            assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        }
    }

    @Test
    public void checkFindById_whenExistingId_thenUserIsReturned() {
        User expectedUser = DataUtil.getTestUser();
        when(userRepository.findById(eq(DataUtil.testUUID(1)))).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findById(DataUtil.testUUID(1));

        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }

    @Test
    public void checkFindById_whenNonExistingId_thenEntityNotFoundExceptionIsThrown() {
        when(userRepository.findById(eq(DataUtil.testUUID(0)))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findById(DataUtil.testUUID(0)));
    }

    @Test
    public void checkFindByEmail_whenExistingEmail_thenUserIsReturned() {
        User expectedUser = DataUtil.getTestUser();
        String email = expectedUser.getEmail();
        when(userRepository.findUserByEmail(eq(email))).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findByEmail(email);

        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }

    @Test
    public void checkFindByEmail_whenNonExistingEmail_thenEntityNotFound() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findByEmail("test@test.com"));
    }

    @Test
    public void checkCreate_whenValidUser_thenUserIsReturned() {
        User expectedUser = DataUtil.getTestUser();
        Role testRole = DataUtil.getTestRole();
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);
        when(roleRepository.findByName("USER")).thenReturn(testRole);
        when(passwordEncoder.encode(expectedUser.getPassword())).thenReturn("encPassword");
        User actualUser = userService.create(expectedUser, "user1");

        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals("encPassword", actualUser.getPassword());
    }

    @Test
    public void checkCreate_whenEmailAlreadyExisting_thenThrowConstraintViolation() {
        User user = DataUtil.getTestUser();
        when(userRepository.save(eq(user))).thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class, () -> userService.create(user, "user1"));
    }

    @Test
    public void checkUpdate_whenValidUser_thenChangedUserIsReturned() {
        String newEmail = "test@test.com";

        User unchangedUser = DataUtil.getTestUser();
        when(userRepository.findById(eq(DataUtil.testUUID(1)))).thenReturn(Optional.of(unchangedUser));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User changedUser = DataUtil.getTestUser();
        changedUser.setEmail(newEmail);
        User updatedUser = userService.update(changedUser, DataUtil.testUUID(1));

        assertEquals(newEmail, updatedUser.getEmail());
    }

    @Test
    public void checkUpdate_whenBlankEmail_thenFailedValidationExceptionIsThrown() {
        User unchangedUser = DataUtil.getTestUser();
        when(userRepository.findById(eq(DataUtil.testUUID(1)))).thenReturn(Optional.of(unchangedUser));

        User changedUser = DataUtil.getTestUser();
        changedUser.setEmail("");

        assertThrows(FailedValidationException.class, () -> userService.update(changedUser, DataUtil.testUUID(1)));
    }

    @Test
    public void checkUpdate_whenNonExistingId_thenEntityNotFoundExceptionIsThrown() {
        User user = DataUtil.getTestUser();
        when(userRepository.findById(eq(DataUtil.testUUID(1)))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.update(user, DataUtil.testUUID(0)));
    }

    @Test
    public void checkUpdate_whenEmailAlreadyExists_thenThrowConstraintViolation() {
        User user = DataUtil.getTestUser();
        when(userRepository.save(eq(user))).thenThrow(ConstraintViolationException.class);
        when(userRepository.findById(eq(DataUtil.testUUID(1)))).thenReturn(Optional.of(user));
        assertThrows(ConstraintViolationException.class, () -> userService.update(user, DataUtil.testUUID(1)));
    }

    @Test
    public void checkDeleteById_whenNonExistingId_thenEntityNotFoundExceptionIsThrown() {
        doThrow(new EntityNotFoundException()).when(userRepository).deleteById(DataUtil.testUUID(0));
        assertThrows(EntityNotFoundException.class, () -> userService.deleteById(DataUtil.testUUID(0)));
    }

}
