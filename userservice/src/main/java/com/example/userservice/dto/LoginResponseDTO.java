package com.example.userservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdAt;
    private List<AddressDTO> addresses;

}
