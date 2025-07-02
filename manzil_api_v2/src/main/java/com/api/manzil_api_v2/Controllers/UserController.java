package com.api.manzil_api_v2.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard()
	{
        return "👑 Welcome to Admin Dashboard!";
    }
	
	@GetMapping("/tenant/profile")
    @PreAuthorize("hasRole('TENANT')")
    public String tenantProfile() 
	{
        return "🏠 Welcome to Tenant Profile!";
    }

	@GetMapping("/common/info")
    @PreAuthorize("hasAnyRole('ADMIN', 'TENANT')")
    public String commonInfo() 
	{
        return "ℹ️ This is common information for all users.";
    }
}
