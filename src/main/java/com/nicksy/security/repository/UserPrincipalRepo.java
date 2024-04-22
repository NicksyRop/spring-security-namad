package com.nicksy.security.repository;

import com.nicksy.security.entity.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPrincipalRepo extends JpaRepository<UserPrincipal, Long> {
    Optional<UserPrincipal> findByUsername(String username);
}
