package com.example.spring_auth_service.repository;

import com.example.spring_auth_service.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(value = "User.rolesWithPermissions")
    Optional<User> findByUsername(String username);
    @EntityGraph(value = "User.rolesWithPermissions")
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
