package com.api.manzil_api_v2.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Tenant {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String fullName;
    private String roomNumber;
    private Double rent;

    @OneToOne
    private User user;  // Link to user account

}
