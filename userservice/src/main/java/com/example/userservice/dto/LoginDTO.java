package com.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid Email address")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
