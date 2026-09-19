package com.example.MaupinAirlineTicketSystem.serviceImplementation;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Airport;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.repository.AdminAirlineRepository;
import com.example.MaupinAirlineTicketSystem.repository.AirportRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.service.FlightSearchService;

@Service
public class FlightSearchServiceImpl implements FlightSearchService{

	 @Autowired
	    private FlightPlanRepository flightPlanRepository;

	 @Autowired
	 private AirportRepository airportRepository;
	 
	 @Autowired
	 private AdminAirlineRepository airlineRepository;
	 
	 @Override
	    public List<Airport> getAllAirports() {
	        return airportRepository.findAll();
	    }

	    @Override
	    public List<FlightPlan> searchFlights(
	            String departureCity,
	            String arrivalCity,
	            LocalDate departureDate
	            ) {

	        return flightPlanRepository
	                .findByDepartureAirport_CityAndArrivalAirport_CityAndDepartureTimeBetween(
	                        departureCity,
	                        arrivalCity,
	                        departureDate.atStartOfDay(),
	                        departureDate.atTime(23,59,59)                  
	                );
	    }

	    @Override
	    public List<FlightPlan> searchFlight(
	            int airlineId,
	            String date) {

	        LocalDate d = LocalDate.parse(date);

	        return flightPlanRepository
	                .findByFlight_Airline_AirlineIdAndDepartureTimeBetween(
	                        airlineId,
	                        d.atStartOfDay(),
	                        d.atTime(23,59,59));
	    }

		@Override
		public List<Airline> getAllAirlines() {
			// TODO Auto-generated method stub
			 List<Airline> list = airlineRepository.findAll();

			    System.out.println(list);

			    return list;
		}

}
