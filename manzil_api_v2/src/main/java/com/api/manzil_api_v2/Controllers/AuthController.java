package com.api.manzil_api_v2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.manzil_api_v2.Dto.AuthResponse;
import com.api.manzil_api_v2.Dto.LoginRequest;
import com.api.manzil_api_v2.Dto.RegisterRequest;
import com.api.manzil_api_v2.Service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired private AuthService authService;
	
	  @PostMapping("/register")
	    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest r) {
	        return ResponseEntity.ok(authService.register(r));
	    }

	  @PostMapping("/login")
	    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest r) {
	        return ResponseEntity.ok(authService.login(r));
	    }
	  
}
