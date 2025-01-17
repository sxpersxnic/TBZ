package com.m320.api.service;

import com.m320.api.lib.exceptions.ExceptionMessages;
import com.m320.api.lib.exceptions.FailedValidationException;
import com.m320.api.lib.validation.Validator;
import com.m320.api.model.User;
import com.m320.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User newEmail(String oldEmail, String newEmail) {
        User user = userRepository.findByEmail(oldEmail).orElseThrow(EntityNotFoundException::new);
        Map<String, List<String>> errors = new HashMap<>();
        if (Validator.isValidEmail(newEmail)) {
            user.setEmail(newEmail);
            return userRepository.save(user);
        } else {
            errors.put("Email", List.of(ExceptionMessages.getInvalidMessage("Email")));
            throw new FailedValidationException(errors);
        }
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
