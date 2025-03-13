package com.f5.commit_or_die.services;
import org.springframework.stereotype.Service;

import com.f5.commit_or_die.model.User;
import com.f5.commit_or_die.repository.UserRepository;

@Service
public class AuthService {
    private  UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   
    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public User register(User user) {
        return userRepository.save(user);
    }
}