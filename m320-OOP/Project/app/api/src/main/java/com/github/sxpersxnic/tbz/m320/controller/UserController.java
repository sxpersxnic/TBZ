package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.payload.mapper.UserMapper;
import com.github.sxpersxnic.tbz.m320.service.UserService;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.UserRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@RestController
@RequestMapping(path = UserController.PATH)
public class UserController {
    public static final String PATH = "/users";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET
    // DOCUMENTATION
    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users found!",
                content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    // METHOD
    public ResponseEntity<?> findAll() {
        try {
            List<User> users = userService.findAll();
            System.out.println(users);
            return ResponseEntity.status(HttpStatus.OK).body(
                    users.stream()
                         .map(UserMapper::toDTO)
                         .toList()
                    );
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }
    }
    // GET
    // DOCUMENTATION
    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found!",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found!",
                    content = @Content)
    })
    // METHOD
    public ResponseEntity<?> findById(@Parameter(description = "Id of user to find") @PathVariable UUID id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(user));
        } catch (EntityNotFoundException e ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
    }
    // PATCH
    // DOCUMENTATION
    @PatchMapping("/{id}")
    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully!",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User could not be found!",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "There occurred a conflict while updating the user",
                    content = @Content)
    })
    // METHOD
    public ResponseEntity<?> update(
            @Parameter(description = "User to update")
            @RequestBody UserRequestDTO updateUserDTO,

            @Parameter(description = "Id of user to update")
            @PathVariable UUID id
    ) {
        try {
            User updateUser = UserMapper.fromDTO(updateUserDTO);
            User savedUser = userService.update(updateUser, id);
            return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(savedUser));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There occurred a conflict while updating the user");
        }
    }

    // DELETE
    // DOCUMENTATION
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User was deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User could not be deleted",
                    content = @Content)
    })
    // METHOD
    public ResponseEntity<?> delete(@Parameter(description = "Id of user to delete") @PathVariable UUID id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User to be deleted was not found!");
        }
    }
}
