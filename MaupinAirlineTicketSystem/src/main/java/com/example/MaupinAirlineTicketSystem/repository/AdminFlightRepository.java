package com.example.MaupinAirlineTicketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;
import com.example.MaupinAirlineTicketSystem.entity.Flight;
import java.util.Optional;



@Repository
public interface AdminFlightRepository extends JpaRepository<Flight, Integer>{

	Optional<Flight> findByFlightNumberIgnoreCase(String flightNumber);

}
