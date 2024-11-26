package m320.projekt.service;

import jakarta.persistence.EntityNotFoundException;
import m320.projekt.lib.exceptions.UserAlreadyExistsException;
import m320.projekt.lib.interfaces.AuthenticationService;
import m320.projekt.lib.jwt.JwtGenerator;
import m320.projekt.lib.validation.UserValidator;
import m320.projekt.model.User;
import m320.projekt.payload.dto.request.SignInReqDTO;
import m320.projekt.payload.dto.request.SignUpReqDTO;
import m320.projekt.payload.dto.response.SignInResDTO;
import m320.projekt.payload.dto.response.SignUpResDTO;
import m320.projekt.payload.mapper.SignInMapper;
import m320.projekt.payload.mapper.SignUpMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService implements AuthenticationService<SignUpReqDTO, SignUpResDTO, SignInReqDTO, SignInResDTO> {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SignInMapper signInMapper;
    private final UserValidator userValidator;

    public AuthService(UserService userService, AuthenticationManager authenticationManager, SignInMapper signInMapper, UserValidator userValidator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.signInMapper = signInMapper;
        this.userValidator = userValidator;
    }

    public SignUpResDTO signUp(SignUpReqDTO dto) {
        Map<String, List<String>> errors = new HashMap<>();
        User newUser = new User();
        User validatedUser = userValidator.validateCredentials(dto, newUser);

        if (userService.existsByUsername(validatedUser.getUsername())) {
            errors.put("username", List.of("Username is already in use!"));
        }
        if (userService.existsByEmail(validatedUser.getEmail())) {
            errors.put("email", List.of("Email is already in use!"));
        }
        if (!errors.isEmpty()) {
            throw new UserAlreadyExistsException(errors);
        }
        User responseUser = userService.create(validatedUser);
        return SignUpMapper.toDTO(responseUser);
    }

    public SignInResDTO signIn(SignInReqDTO dto) {
        User requestedUser = signInMapper.fromDTO(dto);
        String password = dto.getPassword();

        if (requestedUser.getEmail() != null) {
            requestedUser = userService.findByEmail(requestedUser.getEmail());
        } else if (requestedUser.getUsername() != null) {
            requestedUser = userService.findByUsername(requestedUser.getUsername());
        } else {
            throw new EntityNotFoundException();
        }

        Authentication token = new UsernamePasswordAuthenticationToken(requestedUser.getUsername(), password);

        if (authenticationManager.authenticate(token).isAuthenticated()) {
            String jwt = JwtGenerator.generateJwtToken(requestedUser.getUsername());
            return signInMapper.toDTO(requestedUser, jwt);
        } else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }
}
