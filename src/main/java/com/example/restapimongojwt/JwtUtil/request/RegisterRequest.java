package com.example.restapimongojwt.JwtUtil.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @NotBlank
    @Email
    @Size(min = 5, max = 50)
    private String email;

    @Size(min = 8, max = 30)
    @NotBlank
    private String password;
}
