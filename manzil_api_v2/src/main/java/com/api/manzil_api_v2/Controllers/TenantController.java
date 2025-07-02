package com.api.manzil_api_v2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.manzil_api_v2.Entities.Tenant;
import com.api.manzil_api_v2.Entities.User;
import com.api.manzil_api_v2.Repository.UserRepository;
import com.api.manzil_api_v2.Service.TenantService;

@RestController
@RequestMapping("/api")
public class TenantController {
	
	@Autowired private TenantService tenantService;
    @Autowired private UserRepository userRepository;
    
 // TENANT view own bill
    @GetMapping("/tenants/my-bill")
    @PreAuthorize("hasRole('TENANT')")
    public Tenant getMyBill() 
    {
        return tenantService.getMyBill();
    }

 // ADMIN view all tenants
    @GetMapping("/admin/tenants")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tenant> getAllTenants()
    {
        return tenantService.getAllTenants();
    }
    
    // ADMIN create new tenant and link to user
    @PostMapping("/admin/create-tenant")
    @PreAuthorize("hasRole('ADMIN')")
    public Tenant createTenant(@RequestParam String email,
                               @RequestParam String fullName,
                               @RequestParam String roomNumber,
                               @RequestParam double rent) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return tenantService.createTenant(fullName, roomNumber, rent, user);
    }
}
