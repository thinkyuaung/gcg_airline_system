package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airport;

@Service
public interface AdminAirportService {

	public List<Airport> getAllAirports();

	public Airport getAirportById(int id);

	public Airport saveAirport(Airport airport);
	
	public void deleteAirportById(int id);

}
