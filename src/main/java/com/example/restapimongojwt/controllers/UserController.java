package com.example.restapimongojwt.controllers;

import com.example.restapimongojwt.JwtUtil.JwtUtil;
import com.example.restapimongojwt.models.User;
import com.example.restapimongojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    private ResponseEntity<Object> register(@RequestBody User user)
    {
        JwtUtil jwtUtil = new JwtUtil();
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with this email already exists");
        }

        String encryptedPassword = jwtUtil.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user);

        Map<String, Object> response = Map.of(
                "token", token,
                "userEmail", user.getEmail()

        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    private ResponseEntity<Object> login(@RequestBody User user)
    {
        return null;
    }







}
