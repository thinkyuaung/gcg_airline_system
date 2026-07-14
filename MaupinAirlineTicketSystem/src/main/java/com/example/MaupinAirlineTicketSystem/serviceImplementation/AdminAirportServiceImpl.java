package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airport;
import com.example.MaupinAirlineTicketSystem.repository.AdminAirportRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminAirportService;

@Service
public class AdminAirportServiceImpl implements AdminAirportService {

	@Autowired
	AdminAirportRepository adminAirportRepo;

	@Override
	public List<Airport> getAllAirports() {
		return adminAirportRepo.findAll();
	}

	@Override
	public Airport getAirportById(int id) {
		Optional<Airport> optional = adminAirportRepo.findById(id);
		return optional.orElse(null);
	}

}
