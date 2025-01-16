package com.m320.api.controller;

import com.m320.api.lib.exceptions.FailedValidationException;
import com.m320.api.lib.utils.Account;
import com.m320.api.model.Profile;
import com.m320.api.payload.dto.request.auth.SignInRequestDTO;
import com.m320.api.payload.dto.request.auth.SignUpRequestDTO;
import com.m320.api.payload.dto.response.auth.SignUpResponseDTO;
import com.m320.api.payload.mapper.auth.SignUpMapper;
import com.m320.api.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.m320.api.lib.constants.Routes.*;

@RestController
@RequestMapping(path = AUTH_CONTROLLER_ROUTE)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(SIGN_IN_ROUTE)
    public ResponseEntity<?> signIn(@RequestBody SignInRequestDTO dto) {
        try {
            String token = authService.signIn(dto.getEmail(), dto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such account");
        }
    }

    @PostMapping(SIGN_UP_ROUTE)
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO dto) {
        try {
            Account acc = SignUpMapper.fromDTO(dto);
            Profile profile = authService.signUp(acc.getUser(), acc.getProfile());
            SignUpResponseDTO response = SignUpMapper.toDTO(profile);
            return ResponseEntity.ok().body(response);
        } catch (FailedValidationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getErrors().toString());
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already exists");
        }
    }
}
