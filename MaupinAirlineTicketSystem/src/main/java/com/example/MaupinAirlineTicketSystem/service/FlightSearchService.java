package com.example.MaupinAirlineTicketSystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Airport;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.service.FlightSearchService;

@Service
public interface FlightSearchService {
	
	List<FlightPlan> searchFlights(
            String departureCity,
            String arrivalCity,
            LocalDate departureDate);
	
	List<Airport> getAllAirports();
	
	List<Airline> getAllAirlines();
	
	List<FlightPlan> searchFlight(
            int airlineId,
            String date);
}
