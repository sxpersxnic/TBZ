package com.m320.api.service;

import com.m320.api.model.User;
import com.m320.api.payload.dto.response.UserResponseDTO;
import com.m320.api.payload.mapper.UserMapper;
import com.m320.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return UserMapper.toDTO(user);
    }
}
