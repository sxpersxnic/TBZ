package com.m320.api.controller;

import com.m320.api.payload.dto.request.auth.SignInRequestDTO;
import com.m320.api.payload.dto.request.auth.SignUpRequestDTO;
import com.m320.api.payload.dto.response.auth.SignInResponseDTO;
import com.m320.api.payload.dto.response.auth.SignUpResponseDTO;
import com.m320.api.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

    @PostMapping(SIGN_UP_ROUTE)
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDTO dto) {
        try {
            SignUpResponseDTO response = authService.signUp(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sign up credentials");
        }
    }

    @PostMapping(SIGN_IN_ROUTE)
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequestDTO dto) {
        try {
            SignInResponseDTO response = authService.signIn(dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sign in credentials");
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

}
