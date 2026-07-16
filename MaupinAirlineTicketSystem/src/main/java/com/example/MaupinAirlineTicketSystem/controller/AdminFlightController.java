package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Flight;
import com.example.MaupinAirlineTicketSystem.entity.FlightFeature;
import com.example.MaupinAirlineTicketSystem.repository.AdminSeatRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminAirlineService;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightFeatureService;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;

@Controller
@RequestMapping("/airline")

public class AdminFlightController {

	@Autowired
	AdminFlightService adminFlightService;

	@Autowired
	AdminAirlineService adminAirlineService;
	
	@Autowired
	AdminFlightFeatureService featureService;
	
	@Autowired
	AdminSeatRepository seatRepo;

	@GetMapping("/admin/flightForm")
	public String create(Model model) {
		// just to show form and empty student object

		List<Airline> airlines = adminAirlineService.getAllAirlines();
		List<FlightFeature> features = featureService.getAllFeatures();

		Flight f = new Flight();
		
		model.addAttribute("flight", f);
		model.addAttribute("airlines", airlines);
		model.addAttribute("features",features);

		return "Admin/AddFlight";
	}

	// No photo
	@PostMapping("/admin/flight")
	public String saveFlights(@ModelAttribute("flight") Flight flight, @RequestParam("airlineId") int aId) {
		flight.setStatus("active");

		// Course c = courseService.getCourseById(cId);
		Airline a = adminAirlineService.getAirlineById(aId);

		flight.setAirline(a);

		adminFlightService.saveFlight(flight);
		return "redirect:/airline/admin/flights";
	}

	@GetMapping("/admin/flights")
	public String flightList(Model model) {

		List<Flight> flights = adminFlightService.getAllFlights();
		
		model.addAttribute("flights", flights);
	    model.addAttribute("activeTab", "flights");

		return "Admin/admin";
	}

	@GetMapping("/admin/flight/edit/{id}")
	public String editFlight(@PathVariable("id") int id, Model model) {
		Flight flight = adminFlightService.getFlightById(id);
		List<Airline> airlines = adminAirlineService.getAllAirlines();

		model.addAttribute("flight", flight);
		model.addAttribute("airlines", airlines);

		return "Admin/AddFlight";
	}

	@PostMapping("/admin/flight/update")
	public String updateFlight(@ModelAttribute("flight") Flight flight, @RequestParam("airlineId") int aId) {
		Airline a = adminAirlineService.getAirlineById(aId);
		flight.setAirline(a);

		adminFlightService.saveFlight(flight);
		return "redirect:/airline/admin/flights";
	}

	@GetMapping("/admin/flight/delete/{id}")
	public String deleteFlight(@PathVariable("id") int id) {
		adminFlightService.deleteFlightById(id);
		return "redirect:/airline/admin/flights";
	}

}
