package com.example.restapimongojwt.controllers;

import com.example.restapimongojwt.JwtUtil.JwtUtil;
import com.example.restapimongojwt.models.User;
import com.example.restapimongojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Encrypted;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    private String register(@RequestBody User user)
    {
        if(userRepository.existsByEmail(user.getEmail()))
        {
            return "User with this email already exists";
        }
        String encryptedPassword= JwtUtil.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        String token = JwtUtil.generateToken(user);

        return token;
    }





}
