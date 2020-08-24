package com.diary.demo.repository;

import com.diary.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByGoogleUsername(String googleUsername);
    User findByGoogleName(String googleName);
}
