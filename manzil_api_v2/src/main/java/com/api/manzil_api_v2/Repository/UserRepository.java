package com.api.manzil_api_v2.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.manzil_api_v2.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);

}
