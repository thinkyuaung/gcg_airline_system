package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airport;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.repository.AirportRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.service.FlightSearchService;

@Service
public class FlightSearchServiceImpl implements FlightSearchService{

	 @Autowired
	    private FlightPlanRepository flightPlanRepository;

	 @Autowired
	 private AirportRepository airportRepository;
	 
	 @Override
	    public List<FlightPlan> searchFlights(
	            String departureCity,
	            String arrivalCity,
	            LocalDate departureDate) {


	        LocalDateTime start =
	                departureDate.atStartOfDay();


	        LocalDateTime end =
	                departureDate.atTime(23,59,59);



	        return flightPlanRepository
	                .findByDepartureAirport_CityAndArrivalAirport_CityAndDepartureTimeBetween(
	                        departureCity,
	                        arrivalCity,
	                        start,
	                        end
	                );

	    }

	 @Override
	 public List<Airport> getAllAirports() {
		// TODO Auto-generated method stub
		 return airportRepository.findAll();
	 }
}
