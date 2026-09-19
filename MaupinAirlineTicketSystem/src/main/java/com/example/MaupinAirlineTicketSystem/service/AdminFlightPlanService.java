package com.example.MaupinAirlineTicketSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;

@Service
public interface AdminFlightPlanService {

	public FlightPlan saveFlightPlan(FlightPlan flightPlan);

	public List<FlightPlan> getAllFlightPlans();

	public FlightPlan getFlightPlanById(int id);

	public void deleteFlightPlanById(int id);

}
