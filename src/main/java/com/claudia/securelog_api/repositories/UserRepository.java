package com.claudia.securelog_api.repositories;

import com.claudia.securelog_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

// this repository manages User entity with ID = Long