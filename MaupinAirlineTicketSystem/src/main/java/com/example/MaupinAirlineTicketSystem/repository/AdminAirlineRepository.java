package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import java.util.Optional;

@Repository
public interface AdminAirlineRepository extends JpaRepository<Airline, Integer>{

	Optional<Airline> findByAirlineNameIgnoreCase(String airlineName);

	Optional<Airline> findByAirlineCodeIgnoreCase(String airlineCode);

}
