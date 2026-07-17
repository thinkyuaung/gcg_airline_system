package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.MaupinAirlineTicketSystem.entity.Cancellation;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {

	
}
