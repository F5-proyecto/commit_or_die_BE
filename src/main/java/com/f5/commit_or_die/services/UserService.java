package com.f5.commit_or_die.services;

import org.springframework.stereotype.Service;

import com.f5.commit_or_die.model.User;
import com.f5.commit_or_die.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

      public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById (Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void update(User user) {
        userRepository.save(user);
    }
}
