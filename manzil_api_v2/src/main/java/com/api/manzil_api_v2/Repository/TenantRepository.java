package com.api.manzil_api_v2.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.manzil_api_v2.Entities.Tenant;
import com.api.manzil_api_v2.Entities.User;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
	Optional<Tenant> findByUser(User user);

}
