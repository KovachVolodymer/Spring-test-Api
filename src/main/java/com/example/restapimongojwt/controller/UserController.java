package com.example.restapimongojwt.controller;

import com.example.restapimongojwt.model.User;
import com.example.restapimongojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
