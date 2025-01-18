package com.m320.api.controller;

import com.m320.api.payload.dto.request.ProfileRequestDTO;
import com.m320.api.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static com.m320.api.lib.constants.Routes.*;

@RestController
@RequestMapping(PROFILE_CONTROLLER_ROUTE)
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.findAll());
    }

    @GetMapping(GET_ROUTE)
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profileService.findById(id));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }
    }

    @PatchMapping(PATCH_ROUTE)
    public ResponseEntity<?> patch(@Valid @RequestBody ProfileRequestDTO dto, @PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profileService.update(dto, id));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Profile already exists");
        }
    }

    @DeleteMapping(DELETE_ROUTE)
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            profileService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }
    }
}
