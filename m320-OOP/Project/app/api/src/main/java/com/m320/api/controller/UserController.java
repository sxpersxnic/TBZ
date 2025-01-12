package com.m320.api.controller;

import com.m320.api.model.User;
import com.m320.api.payload.dto.response.UserResponseDTO;
import com.m320.api.payload.mapper.UserMapper;
import com.m320.api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll().stream().map(UserMapper::toDTO).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        try {
            User user = userService.getUserById(id);
            UserResponseDTO dto = UserMapper.toDTO(user);

            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
