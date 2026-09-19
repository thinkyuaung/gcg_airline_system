package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.MaupinAirlineTicketSystem.entity.Airport;
@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
	
}