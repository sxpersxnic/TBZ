package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.payload.dto.RoleDTO;
import com.github.sxpersxnic.tbz.m320.payload.mapper.RoleMapper;
import com.github.sxpersxnic.tbz.m320.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@RestController
@RequestMapping(path = RoleController.PATH)
public class RoleController {
    public static final String PATH = "/roles";
    public final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Get a role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content)
    })
    public ResponseEntity<?> findById(
            @Parameter(description = "Id of role to get")
            @PathVariable("id") UUID id
    ) {
        try {
            Role role = roleService.findById(id);
            return ResponseEntity.ok(RoleMapper.toDTO(role));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role was not found");
        }
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Get a role by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content)
    })
    public ResponseEntity<?> findByName(
            @Parameter(description = "Name of role to get")
            @PathVariable("name") String name
    ) {
        try {
            Role role = roleService.findByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(RoleMapper.toDTO(role));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role was not found");
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Add a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role was created successfully",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "409", description = "There was a conflict while creating the role",
                    content = @Content)
    })
    public ResponseEntity<?> create(
            @Parameter(description = "The new role to create")
            @Valid @RequestBody RoleDTO newRoleDTO
    ) {
        try {
            Role newRole = RoleMapper.fromDTO(newRoleDTO);
            Role savedRole = roleService.create(newRole);
            return ResponseEntity.status(HttpStatus.CREATED).body(RoleMapper.toDTO(savedRole));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role could not be created");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Delete a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role was deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Role could not be deleted",
                    content = @Content)
    })
    public ResponseEntity<?> delete(
            @Parameter(description = "Id of role to delete")
            @PathVariable("id") UUID id
    ) {
        try {
            roleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role was not found");
        }
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Update a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role was updated successfully",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "There was a conflict while updating the role",
                    content = @Content)
    })
    public ResponseEntity<?> update(
            @Parameter(description = "The role to update")
            @RequestBody RoleDTO updateRoleDTO,

            @Parameter(description = "Id of role to update")
            @PathVariable UUID id) {
        try {
            Role updateRole = RoleMapper.fromDTO(updateRoleDTO);
            Role savedRole = roleService.update(updateRole, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(RoleMapper.toDTO(savedRole));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role could not be created");
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @Operation(summary = "Get all roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles found",
                    content = @Content(schema = @Schema(implementation = RoleDTO.class)))
    })
    public ResponseEntity<?> findAll() {
        List<Role> roles = roleService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(
                roles.stream()
                .map(RoleMapper::toDTO)
                .toList());
    }



}
