package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.repository.AdminAirlineRepository;
import com.example.MaupinAirlineTicketSystem.repository.FlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.service.FlightSearchService;

@Controller
@RequestMapping("/airline")
public class FlightController {

	@Autowired
	private FlightSearchService flightSearchService;

	@Autowired
	private AdminAirlineRepository airlineRepository;
	
	@Autowired
	private FlightPlanRepository flightPlanRepository;

	@GetMapping("/flights")
	public String flights(
	        @RequestParam(required=false) Integer airlineId,
	        @RequestParam(required=false) String departDate,
	        Model model){

	    model.addAttribute(
	            "airlines",
	            flightSearchService.getAllAirlines());

	    List<FlightPlan> flights;

	    if(airlineId != null && departDate != null && !departDate.isBlank()){

	        flights = flightSearchService.searchFlight(
	                airlineId,
	                departDate);

	    }else{

	        flights = flightPlanRepository.findAll();

	    }

	    model.addAttribute("flights", flights);

	    return "flights";
	}
	
	@GetMapping("/flights/search")
	public String searchFlights(

	        @RequestParam int airlineId,
	        @RequestParam String departDate,
	        Model model) {
	    
	    model.addAttribute("airlines", flightSearchService.getAllAirlines());

	    model.addAttribute(
	            "flights",
	            flightSearchService.searchFlight(
	                    airlineId,
	                    departDate));

	    return "flights";
	}
}
