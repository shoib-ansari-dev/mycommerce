package com.example.userservice.controller;

import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                userService.getProfileByEmail(userDetails.getUsername())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getProfileById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UserResponseDTO dto) {
        return ResponseEntity.ok(userService.updateProfile(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> delete(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteByUserId(id));
    }
}