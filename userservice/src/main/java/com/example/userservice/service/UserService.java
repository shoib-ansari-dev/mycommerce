package com.example.userservice.service;

import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.entity.Role;
import com.example.userservice.entity.Users;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO getProfileById(Long userId) {
        Users user = findUserById(userId);
        return loginResponseDTO(user);
    }

    public UserResponseDTO getProfileByEmail(String email) {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return loginResponseDTO(users);
    }

    @Transactional
    public UserResponseDTO updateProfile(Long userId, UserResponseDTO userResponseDTO ){
        Users user = findUserById(userId);

        if (userResponseDTO.getEmail() != null) user.setEmail(userResponseDTO.getEmail());
        if (userResponseDTO.getRole()  != null) user.setRole(
                Role.valueOf(userResponseDTO.getRole())
        );

        userRepository.save(user);
        return loginResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO deleteByUserId(Long id){
        Users user  = findUserById(id);
        userRepository.delete(user);
        return loginResponseDTO(user);
    }

    public Users findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    private UserResponseDTO loginResponseDTO(Users user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
