package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.lib.jwt.JwtGenerator;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.AuthRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.SignInResponseDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.SignUpResponseDTO;
import com.github.sxpersxnic.tbz.m320.payload.mapper.AuthMapper;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author sxpersxnic
 */
@RestController
@RequestMapping(AuthController.PATH)
public class AuthController {

    public static final String PATH = "/auth";

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was created successfully",
                    content = @Content(schema = @Schema(implementation = SignUpResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "User could not be created, username already in use",
                    content = @Content)
    })
    @SecurityRequirements //no security here, default is BEARER
    public ResponseEntity<?> signUp(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        try {
            User user = userService.create(AuthMapper.fromDTO(authRequestDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(AuthMapper.toDTO(user));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User could not be created, username already in use");
        }
    }

    @PostMapping("/signin")
    @Operation(summary = "Receive a token for BEARER authorization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = SignInResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)
    })
    @SecurityRequirements //no security here, default is BEARER
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDTO authRequest) {
        try {
            String email = authRequest.getEmail();
            String password = authRequest.getPassword();

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));

            User user = userService.findByEmail(email);
            String jwt = JwtGenerator.generateJwtToken(authentication);
            return ResponseEntity.ok(new SignInResponseDTO(jwt, user.getId()));
        } catch (BadCredentialsException ignored) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
