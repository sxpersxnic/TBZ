package com.github.sxpersxnic.tbz.m320.data.controller;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.exceptions.GlobalControllerExceptionHandler;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.controller.UserController;
import com.github.sxpersxnic.tbz.m320.service.UserService;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.UserRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.UserResponseDTO;
import com.github.sxpersxnic.tbz.m320.config.SecurityConfiguration;
import com.github.sxpersxnic.tbz.m320.util.DataDTOUtil;
import com.github.sxpersxnic.tbz.m320.util.DataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WithMockUser(authorities = {"SCOPE_MANAGER", "SCOPE_ADMIN"})
@ContextConfiguration(classes = {SecurityConfiguration.class})
@Import({UserController.class, GlobalControllerExceptionHandler.class})
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void prepare() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void checkGet_whenNoParam_thenAllUsersAreReturned() throws Exception {
        List<User> users = DataUtil.getTestUsers();
        List<UserResponseDTO> personDTOs = DataDTOUtil.getTestUserResponseDTOs();
        String expectedResponseBody = objectMapper.writeValueAsString(personDTOs);

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/users")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }

    @Test
    public void checkGetById_whenValidId_thenUserIsReturned() throws Exception {
        User user = DataUtil.getTestUser();
        UserResponseDTO responseDTO = DataDTOUtil.getTestUserResponseDTO();
        String expectedResponseBody = objectMapper.writeValueAsString(responseDTO);
        when(userService.findById(eq(DataUtil.testUUID(1)))).thenReturn(user);

        mockMvc.perform(get("/users/" + DataUtil.testUUID(1))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }

    @Test
    public void checkGetById_whenInvalidId_thenIsNotFound() throws Exception {
        when(userService.findById(eq(DataUtil.testUUID(0)))).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/users/" + DataUtil.testUUID(0))
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void checkPatch_whenValidUser_thenIsOk() throws Exception {
        User user = DataUtil.getTestUser();
        user.setEmail("NewUser@bbcag.ch");
        UserResponseDTO userResponseDTO = DataDTOUtil.getTestUserResponseDTO();
        UserResponseDTO personRequestDTO = DataDTOUtil.getTestUserRequestDTO();
        userResponseDTO.setEmail("NewUser@bbcag.ch");
        String expectedResponseBody = objectMapper.writeValueAsString(userResponseDTO);
        String requestBody = objectMapper.writeValueAsString(personRequestDTO);

        when(userService.update(any(User.class), eq(DataUtil.testUUID(1)))).thenReturn(user);

        mockMvc.perform(patch("/users/update/" + DataUtil.testUUID(1))
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }

    @Test
    public void checkPatch_whenInvalidUser_thenIsBadRequest() throws Exception {
        UserRequestDTO userRequestDTO = DataDTOUtil.getTestUserRequestDTO();
        userRequestDTO.setEmail("");
        String requestBody = objectMapper.writeValueAsString(userRequestDTO);

        when(userService.update(any(User.class), eq(DataUtil.testUUID(1)))).thenThrow(new FailedValidationException(Map.of()));

        mockMvc.perform(patch("/users/update/" + DataUtil.testUUID(1)).
                        contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void checkDelete_whenValidId_thenIsOk() throws Exception {
        mockMvc.perform(delete("/users/delete/" + DataUtil.testUUID(1)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void checkDelete_whenInvalidId_thenIsNotFound() throws Exception {
        doThrow(EmptyResultDataAccessException.class).when(userService).deleteById(DataUtil.testUUID(0));

        mockMvc.perform(delete("/users/delete/" + DataUtil.testUUID(0)))
                .andExpect(status().isNotFound());
    }
}
