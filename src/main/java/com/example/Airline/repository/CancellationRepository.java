package com.example.Airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Airline.entity.Cancellation;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {

	
}
