package com.f5.commit_or_die.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f5.commit_or_die.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
