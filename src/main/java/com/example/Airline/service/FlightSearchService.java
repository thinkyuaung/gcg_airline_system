package com.example.Airline.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Airline.entity.Airport;
import com.example.Airline.entity.FlightPlan;

@Service
public interface FlightSearchService {
	
	List<FlightPlan> searchFlights(
            String departureCity,
            String arrivalCity,
            LocalDate departureDate);
	
	List<Airport> getAllAirports();
}
