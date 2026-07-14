package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.repository.AdminAirlineRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminAirlineService;


@Service
public class AdminAirlineServiceImpl implements AdminAirlineService{

	@Autowired
	AdminAirlineRepository repo;
	
	
	@Override
	public Airline saveFlight(Airline airline) {
		
		return repo.save(airline);
	}
	
	@Override
	public List<Airline> getAllAirlines() {
		List<Airline> airlines = repo.findAll();
		return airlines;
	}

	@Override
	public Airline getAirlineById(int aId) {
		// TODO Auto-generated method stub
		return repo.getById(aId);
	}

}
