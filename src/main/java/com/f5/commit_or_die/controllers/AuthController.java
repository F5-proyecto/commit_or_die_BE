package com.f5.commit_or_die.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f5.commit_or_die.dto.AuthRequest;
import com.f5.commit_or_die.services.AuthService;
import com.f5.commit_or_die.model.User;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        User user = authService.loginAndGetUser(authRequest.getEmail(), authRequest.getPassword());
        Map<String, String> response = new HashMap<>();
        if (user != null) {
            response.put("message", "Login successful");
            response.put("userId", user.getId().toString());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
