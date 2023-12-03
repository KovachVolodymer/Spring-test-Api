package com.example.restapimongojwt.JwtUtil.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {

    @NotBlank
    @Email
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotBlank
    @NotNull(message = "Password cannot be null")
    private String password;



}
