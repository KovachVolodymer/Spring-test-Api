package com.example.restapimongojwt.jwtUtil.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 3,message = "Name must be at least 3 characters")
    @Size(max = 20,message = "Name must be less than 20 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    @Size(max = 50,message = "Email must be less than 50 characters")
    private String email;

    @Size(min = 6,message = "Password must be at least 6 characters")
    @Size(max = 40,message = "Password must be less than 40 characters")
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = "Password must contain at least one uppercase letter, one lowercase letter and one number")
    private String password;
}
