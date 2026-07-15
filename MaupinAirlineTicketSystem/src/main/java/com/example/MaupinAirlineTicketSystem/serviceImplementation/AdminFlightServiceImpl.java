package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.repository.AdminFlightRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;

@Service
public class AdminFlightServiceImpl implements AdminFlightService{
	
	@Autowired
	AdminFlightRepository adminFlightRepo;
   
	@Override
	public Airline saveFlight(Airline airline) {
		// TODO Auto-generated method stub
		return adminFlightRepo.save(airline);
	}

	@Override
	public List<Airline> getAllAirlines() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
