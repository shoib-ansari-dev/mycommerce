package com.example.userservice.security;

import com.example.userservice.entity.Users;
import com.example.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Users> user;
        try {
            user = userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Username or password wrong");
        }
        if(user.isPresent()){
            throw new RuntimeException("Username or password wrong");
        }
        Users foundUser= user.get();
        return User
                .withUsername(foundUser.getEmail())
                .password(foundUser.getPassword())
                .roles(foundUser.getRole().name())
                .build();
    }
}
