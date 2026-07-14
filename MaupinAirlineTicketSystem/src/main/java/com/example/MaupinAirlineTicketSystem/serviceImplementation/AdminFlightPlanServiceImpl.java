package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.repository.AdminFlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightPlanService;

@Service
public class AdminFlightPlanServiceImpl implements AdminFlightPlanService {

	@Autowired
	AdminFlightPlanRepository adminFlightPlanRepo;

	@Override
	public FlightPlan saveFlightPlan(FlightPlan flightPlan) {
		return adminFlightPlanRepo.save(flightPlan);
	}

	@Override
	public List<FlightPlan> getAllFlightPlans() {
		return adminFlightPlanRepo.findAll();
	}

	@Override
	public FlightPlan getFlightPlanById(int id) {
		Optional<FlightPlan> optional = adminFlightPlanRepo.findById(id);
		return optional.orElse(null);
	}

	@Override
	public void deleteFlightPlanById(int id) {
		adminFlightPlanRepo.deleteById(id);
	}

}
