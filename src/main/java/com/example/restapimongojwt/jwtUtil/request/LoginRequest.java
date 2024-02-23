package com.example.restapimongojwt.jwtUtil.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginRequest {

    @NotBlank
    @Email
    @Size(min = 5, max = 50)
    private String email;

    @Size(min = 8, max = 30)
    @NotBlank
    private String password;

}
