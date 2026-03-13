package com.example.userservice.security;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
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
        Optional<User> user;
        try {
            user = userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Username or password wrong");
        }
        if(user.isPresent()){
            throw new RuntimeException("Username or password wrong");
        }
        User foundUser= user.get();
        return org.springframework.security.core.userdetails.User
                .withUsername(foundUser.getMail())
                .password(foundUser.getPassword())
                .roles(foundUser.getRole().name())
                .build();
    }
}
