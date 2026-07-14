package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Flight;
import com.example.MaupinAirlineTicketSystem.repository.AdminFlightRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;

@Service
public class AdminFlightServiceImpl implements AdminFlightService{
	
	@Autowired
	AdminFlightRepository adminFlightRepo;

	@Override
	public Flight saveFlight(Flight flight) {
		
		return adminFlightRepo.save(flight);
	}

	@Override
	public List<Flight> getAllFlights() {
		// TODO Auto-generated method stub
		return adminFlightRepo.findAll();
	}

	



	

}
