package com.claudia.securelog_api.repositories;

import com.claudia.securelog_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

// this repository manages User entity with ID = Long