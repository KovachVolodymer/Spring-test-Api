package com.example.restapimongojwt.controllers;

import com.example.restapimongojwt.JwtUtil.Jwt.JwtUtil;
import com.example.restapimongojwt.JwtUtil.request.LoginRequest;
import com.example.restapimongojwt.JwtUtil.request.RegisterRequest;
import com.example.restapimongojwt.JwtUtil.response.MessageResponse;
import com.example.restapimongojwt.models.User;
import com.example.restapimongojwt.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    private ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest register) {
        if (userRepository.existsByName(register.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(register.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(register.getName(), register.getEmail(), register.getPassword());

        user.setPassword(jwtUtil.encryptPassword(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("User is registered"));
    }

    @PostMapping("/login")
    private ResponseEntity<Object> login(@RequestBody LoginRequest login) {


        User user = userRepository.findByEmail(login.getEmail());

        String token = jwtUtil.generateToken(user);

        Map<Object, String> responseMap = new HashMap<>();
        responseMap.put("name", user.getName());
        responseMap.put("email", user.getEmail());
        responseMap.put("token", token);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);

    }


}
