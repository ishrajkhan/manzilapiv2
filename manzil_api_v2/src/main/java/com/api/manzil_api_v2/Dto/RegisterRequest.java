package com.api.manzil_api_v2.Dto;

import com.api.manzil_api_v2.Entities.Role;

import lombok.Data;

@Data
public class RegisterRequest {

	private String username;
    private String email;
    private String password;
    private Role role;
}
