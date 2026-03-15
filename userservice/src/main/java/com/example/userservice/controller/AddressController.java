package com.example.userservice.controller;


import com.example.userservice.dto.AddressDTO;
import com.example.userservice.service.AddressService;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAll(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.getProfileByEmail(userDetails.getUsername()).getId();
        return ResponseEntity.ok(addressService.getAddresses(userId));
    }

    @GetMapping("/default")
    public ResponseEntity<AddressDTO> getDefault(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.getProfileByEmail(userDetails.getUsername()).getId();
        return ResponseEntity.ok(addressService.getDefaultAddress(userId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = userService.getProfileByEmail(userDetails.getUsername()).getId();
        return ResponseEntity.ok(addressService.countAddresses(userId));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> add(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody AddressDTO dto) {
        Long userId = userService.getProfileByEmail(userDetails.getUsername()).getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressService.addAddress(userId, dto));
    }

    @PatchMapping("/{id}/default")
    public ResponseEntity<AddressDTO> setDefault(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        Long userId = userService.getProfileByEmail(userDetails.getUsername()).getId();
        return ResponseEntity.ok(addressService.setDefaultAddress(userId, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        Long userId = userService.getProfileByEmail(userDetails.getUsername()).getId();
        addressService.deleteAddress(userId, id);
        return ResponseEntity.noContent().build();
    }
}
