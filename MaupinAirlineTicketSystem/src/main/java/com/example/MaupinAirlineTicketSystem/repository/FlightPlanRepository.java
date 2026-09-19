package com.example.MaupinAirlineTicketSystem.repository;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;

public interface FlightPlanRepository extends JpaRepository<FlightPlan, Integer>{

	List<FlightPlan> 
	findByDepartureAirport_CityAndArrivalAirport_CityAndDepartureTimeBetween(
	        String departureCity,
	        String arrivalCity,
	        LocalDateTime start,
	        LocalDateTime end);
	
	List<FlightPlan>
	findByFlight_Airline_AirlineIdAndDepartureTimeBetween(
	        int airlineId,
	        LocalDateTime start,
	        LocalDateTime end);
	
}
