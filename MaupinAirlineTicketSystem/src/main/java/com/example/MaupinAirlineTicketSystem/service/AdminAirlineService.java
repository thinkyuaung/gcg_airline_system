package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airline;

@Service
public interface AdminAirlineService{

	public Airline saveFlight(Airline airline);
	
	public List<Airline> getAllAirlines() ;

	public Airline getAirlineById(int aId);
	
	public void deleteAirlineById(int aId);

}
