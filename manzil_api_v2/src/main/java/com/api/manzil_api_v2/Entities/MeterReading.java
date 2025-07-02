package com.api.manzil_api_v2.Entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor @AllArgsConstructor
public abstract class MeterReading {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Double readingValue;
    private LocalDate readingDate;

    @ManyToOne
    private Tenant tenant;
    
}
