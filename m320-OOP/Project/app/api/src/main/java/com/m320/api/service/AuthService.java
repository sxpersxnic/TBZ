package com.m320.api.service;

import com.m320.api.lib.exceptions.FailedValidationException;
import com.m320.api.model.Profile;
import com.m320.api.model.User;
import com.m320.api.repository.ProfileRepository;
import com.m320.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
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

    public Profile signUp(User user, Profile profile) {
        Map<String, List<String>> errors = new HashMap<>();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        if (userRepository.existsByEmail(user.getEmail())) {
            errors.put("Email", List.of("Email is already in use"));
        } else if (profileRepository.existsByUsername(profile.getUsername())) {
            errors.put("Username", List.of("Username is already taken"));
        } else {
            userRepository.save(user);
            profileRepository.save(profile);
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }

        return profile;
    }

    public String signIn(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        boolean matches = passwordEncoder.matches(password, user.getPassword());

        if (!matches) {
            throw new BadCredentialsException("Invalid Password");
        }

        //TODO: Generate JWT Token
        return "REPLACE_WITH_TOKEN";
    }
}
