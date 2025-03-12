package com.f5.commit_or_die.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Welcome to the commit or die server!");
    }

}
