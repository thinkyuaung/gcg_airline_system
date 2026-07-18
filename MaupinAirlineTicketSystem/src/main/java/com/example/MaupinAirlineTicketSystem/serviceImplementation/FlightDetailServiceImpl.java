package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.entity.SeatClass;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.service.FlightDetailService;

@Service
public class FlightDetailServiceImpl implements FlightDetailService{

	@Autowired
    private FlightPlanRepository flightPlanRepository;
	
    @Autowired
    private SeatClassRepository seatClassRepository;
	
	@Override
	public FlightPlan getFlightPlan(int id) {
		// TODO Auto-generated method stub
		return flightPlanRepository
                .findById(id)
                .orElse(null);
	}

	

	@Override
	public List<SeatClass> getSeatClasses() {
		// TODO Auto-generated method stub
		return seatClassRepository.findAll();
	}

}
