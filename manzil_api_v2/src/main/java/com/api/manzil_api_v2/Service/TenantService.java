package com.api.manzil_api_v2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.manzil_api_v2.Entities.Tenant;
import com.api.manzil_api_v2.Entities.User;
import com.api.manzil_api_v2.Repository.TenantRepository;
import com.api.manzil_api_v2.Repository.UserRepository;

@Service
public class TenantService {
	
	@Autowired private TenantRepository tenantRepo;	
    @Autowired private UserRepository userRepo;

    
    public Tenant getMyBill() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow();
        return tenantRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Tenant not found"));
    }
    
    public List<Tenant> getAllTenants() {
        return tenantRepo.findAll();
    }
    
    public Tenant createTenant(String fullName, String roomNumber, double rent, User user) {
        Tenant t = new Tenant(null, fullName, roomNumber, rent, user);
        return tenantRepo.save(t);
    }
}
