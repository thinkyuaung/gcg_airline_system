package com.example.Airline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Airline.entity.Flight;
import com.example.Airline.entity.FlightFeature;
import com.example.Airline.entity.FlightPlan;
import com.example.Airline.entity.SeatClass;
import com.example.Airline.repository.FlightFeatureRepository;
import com.example.Airline.repository.FlightPlanRepository;
import com.example.Airline.repository.SeatClassRepository;
import com.example.Airline.service.FlightDetailService;

@Service
public class FlightDetailServiceImpl implements FlightDetailService{

	@Autowired
    private FlightPlanRepository flightPlanRepository;
	
	@Autowired
    private FlightFeatureRepository flightFeatureRepository;


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
	public List<FlightFeature> getFeatures(int flightId) {
		// TODO Auto-generated method stub
		return flightFeatureRepository
                .findByFlight(
                        new Flight(flightId)
                    );
	}

	@Override
	public List<SeatClass> getSeatClasses() {
		// TODO Auto-generated method stub
		return seatClassRepository.findAll();
	}

}
