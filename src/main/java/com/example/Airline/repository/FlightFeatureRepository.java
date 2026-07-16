package com.example.Airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Airline.entity.Flight;
import com.example.Airline.entity.FlightFeature;

public interface FlightFeatureRepository extends JpaRepository<FlightFeature, Integer>{

	 List<FlightFeature> findByFlight(Flight flight);
	 
}
