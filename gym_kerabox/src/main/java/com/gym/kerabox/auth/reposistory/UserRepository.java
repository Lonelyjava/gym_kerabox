package com.gym.kerabox.auth.reposistory;

/**
 * 
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gym.kerabox.auth.entity.Role;
import com.gym.kerabox.auth.entity.User;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

	Optional<User> findByRole(Role role);
}