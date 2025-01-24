package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.ProfileRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.mapper.ProfileMapper;
import com.github.sxpersxnic.tbz.m320.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(profileService.findAll().stream().map(ProfileMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ProfileMapper.toDTO(
                            profileService.findById(id)
                    )
            );
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody ProfileRequestDTO changingDTO,
            @PathVariable UUID id
    ) {
        try {
            Profile changing = ProfileMapper.fromDTO(changingDTO);
            Profile saved = profileService.update(changing, id);
            return ResponseEntity.status(HttpStatus.OK).body(ProfileMapper.toDTO(saved));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }
    }
}
