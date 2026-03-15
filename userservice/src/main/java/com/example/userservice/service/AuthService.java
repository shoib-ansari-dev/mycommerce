package com.example.userservice.service;

import com.example.userservice.dto.LoginDTO;
import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.dto.RegisterUserDTO;
import com.example.userservice.entity.Role;
import com.example.userservice.entity.Users;
import com.example.userservice.exception.EmailAlreadyUsedException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    @Transactional
    public UserResponseDTO register(RegisterUserDTO registerUserDTO){
        if(userRepository.existsByEmail(registerUserDTO.getEmail())){
            throw  new EmailAlreadyUsedException("Email aready in use "+ registerUserDTO.getEmail());
        }
        Users users= Users.builder()
                .email(registerUserDTO.getEmail())
                .password(passwordEncoder.encode(registerUserDTO.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(users);

        return toResponseDTO(users);
    }

    public UserResponseDTO loginResponseDTO(LoginDTO loginDTO){

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String token=  jwtTokenProvider.generateToken(String.valueOf(authentication));

        return UserResponseDTO.builder()
                .token(token)
                .id(user.getId())
                .name(user.getEmail())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .addresses(List.of())
                .build();
    }

    private UserResponseDTO toResponseDTO(Users user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
