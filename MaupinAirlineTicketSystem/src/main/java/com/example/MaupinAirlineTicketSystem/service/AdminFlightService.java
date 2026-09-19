package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Flight;

@Service
public interface AdminFlightService {

	public Flight saveFlight(Flight flight);

	public List<Flight> getAllFlights();  

	public Flight getFlightById(int id);

	public void deleteFlightById(int id);

	

}
