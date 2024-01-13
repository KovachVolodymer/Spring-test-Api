package com.example.restapimongojwt.controller;

import com.example.restapimongojwt.model.User;
import com.example.restapimongojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
