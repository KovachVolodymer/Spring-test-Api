package com.example.restapimongojwt.JwtUtil.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    private String email;

    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    @NotBlank(message = "Password is required")
    private String password;
}
