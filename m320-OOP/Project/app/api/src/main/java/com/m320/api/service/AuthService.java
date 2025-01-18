package com.m320.api.service;

import com.m320.api.lib.exceptions.ExceptionMessages;
import com.m320.api.lib.exceptions.FailedValidationException;
import com.m320.api.lib.jwt.JwtGenerator;
import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.payload.dto.request.auth.SignInRequestDTO;
import com.m320.api.payload.dto.request.auth.SignUpRequestDTO;
import com.m320.api.payload.dto.response.auth.SignInResponseDTO;
import com.m320.api.payload.dto.response.auth.SignUpResponseDTO;
import com.m320.api.payload.mapper.auth.SignInMapper;
import com.m320.api.payload.mapper.auth.SignUpMapper;
import com.m320.api.repository.ProfileRepository;
import com.m320.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        Map<String, List<String>> errors = new HashMap<>();

        User user = SignUpMapper.fromDTO(dto);
        Profile profile = user.getProfile().getFirst();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userRepository.existsByEmail(user.getEmail())) {
            errors.put("Email", List.of(ExceptionMessages.getAlreadyInUseMessage("Email")));
        } else if (profileRepository.existsByUsername(profile.getUsername())) {
            errors.put("Username", List.of(ExceptionMessages.getAlreadyInUseMessage("Username")));
        } else {
            userRepository.save(user);
            profileRepository.save(profile);
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }

        return SignUpMapper.toDTO(user, profile);
    }

    @Transactional
    public SignInResponseDTO signIn(SignInRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(EntityNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        Profile profile = profileRepository.findByUserId(user.getId()).orElseThrow(EntityNotFoundException::new);

        String token = JwtGenerator.generateJwtToken(user.getId(), profile.getId());

        return SignInMapper.toDTO(user, profile, token);
    }
}