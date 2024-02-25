package com.example.restapimongojwt.controller;

import com.example.restapimongojwt.jwtUtil.response.MessageResponse;
import com.example.restapimongojwt.model.User;
import com.example.restapimongojwt.repository.UserRepository;
import com.example.restapimongojwt.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;


    @GetMapping("/myProfile")
    public ResponseEntity<Object> getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userRepository.findById(userDetails.getId()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/addRole/{id}")
    public ResponseEntity<Object> addRole(@PathVariable String id,@RequestBody User user)
    {
        Optional<User> userOptional = userRepository.findById(id);
        Set<String> roles = userOptional.get().getRoles();
        roles.addAll(user.getRoles());
        userOptional.get().setRoles(roles);
        userRepository.save(userOptional.get());
        return ResponseEntity.ok().body(new MessageResponse("Add new role"));
    }

}
