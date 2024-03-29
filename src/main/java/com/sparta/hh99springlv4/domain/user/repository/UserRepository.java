package com.sparta.hh99springlv4.domain.user.repository;

import com.sparta.hh99springlv4.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
}


