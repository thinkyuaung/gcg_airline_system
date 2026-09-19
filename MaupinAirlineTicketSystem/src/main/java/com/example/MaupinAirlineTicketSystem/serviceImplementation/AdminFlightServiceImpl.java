package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;
import java.util.Optional;

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
		return adminFlightRepo.findAll();
	}

	@Override
	public Flight getFlightById(int id) {

		return adminFlightRepo.findById(id).get();
	}

	@Override
	public void deleteFlightById(int id) {
		adminFlightRepo.deleteById(id);
	}

	



	

}
