package m320.projekt.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import m320.projekt.payload.dto.request.SignInReqDTO;
import m320.projekt.payload.dto.request.SignUpReqDTO;
import m320.projekt.payload.dto.response.SignInResDTO;
import m320.projekt.payload.dto.response.SignUpResDTO;
import m320.projekt.service.AuthService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static m320.projekt.lib.constants.Security.*;

@RestController
@RequestMapping(AUTH_CONTROLLER_URL)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(SIGN_UP_PATH)
    @SecurityRequirements
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpReqDTO reqDTO) {
        try {
            SignUpResDTO resDTO = authService.signUp(reqDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resDTO);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(SIGN_IN_PATH)
    @SecurityRequirements
    public ResponseEntity<?> signIn(@RequestBody SignInReqDTO reqDTO) {
        try {
            SignInResDTO resDTO = authService.signIn(reqDTO);
            return ResponseEntity.status(HttpStatus.OK).body(resDTO);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
