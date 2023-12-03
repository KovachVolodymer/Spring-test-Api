package com.example.restapimongojwt.controllers;

import com.example.restapimongojwt.JwtUtil.Jwt.JwtUtil;
import com.example.restapimongojwt.JwtUtil.request.LoginRequest;
import com.example.restapimongojwt.models.User;
import com.example.restapimongojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    private ResponseEntity<Object> register(@RequestBody User user) {
        JwtUtil jwtUtil = new JwtUtil();
        if (userRepository.existsByName(user.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body("User name already taken");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already taken!");
        }

        user.setPassword(jwtUtil.encryptPassword(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User is register");
    }

    @PostMapping("/login")
    private ResponseEntity<Object> login(@Valid @RequestBody LoginRequest login) {

        JwtUtil jwtUtil = new JwtUtil();
        User user = userRepository.findByEmail(login.getEmail());

        String token = jwtUtil.generateToken(user);

        Map<Object, String> responseMap = new HashMap<>();
        responseMap.put("name", user.getName());
        responseMap.put("email", user.getEmail());
        responseMap.put("token", token);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);

    }


}
