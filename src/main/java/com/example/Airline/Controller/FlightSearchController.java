package com.example.Airline.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Airline.entity.FlightPlan;
import com.example.Airline.repository.AirportRepository;
import com.example.Airline.service.FlightSearchService;

@Controller
@RequestMapping("/airline")
public class FlightSearchController {
	
	@Autowired
	private FlightSearchService flightSearchService;
	
	@Autowired
	AirportRepository airportRepository;

	@GetMapping("/search")
	public String searchPage(Model model) {
		
		  model.addAttribute("airports",
		            airportRepository.findAll());
		  
		return "userview/searchFlight";
	}
	
	
	@PostMapping("/search")
	public String searchFlights(
			@RequestParam("departureCity") String departureCity,
			@RequestParam("arrivalCity") String arrivalCity,
			@RequestParam("departureDate") LocalDate departureDate,
			@RequestParam("passengers") int passengers,
			@RequestParam("seatClass") String seatClass,
			Model model) {
		
		List<FlightPlan> flightPlans =
				flightSearchService.searchFlights(
						departureCity,
						arrivalCity,
						departureDate
				);
		
		model.addAttribute("flightPlans", flightPlans);
 
		// keep user's choices for next step
		model.addAttribute("passengers", passengers);
 
		model.addAttribute("seatClass", seatClass);
 
 
		return "userview/searchResult";
	}
}
