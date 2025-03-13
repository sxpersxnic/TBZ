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
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

import static com.github.sxpersxnic.tbz.m320.lib.constants.Controller.*;

/**
 * @author sxpersxnic
 */
@RestController
@RequestMapping(USERS)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MODERATOR')")
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users found!",
                content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService
                        .findAll()
                        .stream()
                        .map(UserMapper::toDTO)
                        .toList()
                );
    }
    // GET
    // DOCUMENTATION
    @GetMapping(ID_GET)
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
    @PatchMapping(PATCH)
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
            @Valid @RequestBody UserRequestDTO updateUserDTO,

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
    @DeleteMapping(DELETE)
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User was deleted successfully",
                    content = @Content),
    })
    // METHOD
    public ResponseEntity<?> delete(@Parameter(description = "Id of user to delete") @PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
