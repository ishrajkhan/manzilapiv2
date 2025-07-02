package com.api.manzil_api_v2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.manzil_api_v2.Config.JwtService;
import com.api.manzil_api_v2.Dto.AuthResponse;
import com.api.manzil_api_v2.Dto.LoginRequest;
import com.api.manzil_api_v2.Dto.RegisterRequest;
import com.api.manzil_api_v2.Entities.User;
import com.api.manzil_api_v2.Repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired private UserRepository repo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtService jwtService;
    @Autowired private AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest r) {
        if (repo.existsByEmail(r.getEmail())) throw new RuntimeException("Email exists");
        User u = new User(null, r.getUsername(), r.getEmail(), encoder.encode(r.getPassword()), r.getRole());
        repo.save(u);
        return new AuthResponse(jwtService.generateToken(u));
    }
    
    public AuthResponse login(LoginRequest r) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(r.getEmail(), r.getPassword()));
        User u = repo.findByEmail(r.getEmail()).orElseThrow();
        return new AuthResponse(jwtService.generateToken(u));
    }
}
