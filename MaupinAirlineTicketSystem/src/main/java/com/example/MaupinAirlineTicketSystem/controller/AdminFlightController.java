package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Flight;
import com.example.MaupinAirlineTicketSystem.service.AdminAirlineService;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;

@Controller
@RequestMapping("/airline")

public class AdminFlightController {

	@Autowired
	AdminFlightService adminFlightService;

	@Autowired
	AdminAirlineService adminAirlineService;

	@GetMapping("/admin/flightForm")
	public String create(Model model) {
		// just to show form and empty student object

		List<Airline> airlines = adminAirlineService.getAllAirlines();

		for (Airline a : airlines) {
			System.out.println("ID" + a.getAirlineId());
		}

		Flight f = new Flight();
		model.addAttribute("flight", f);
		model.addAttribute("airlines", airlines);

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
		return "redirect:/airline/admin";
	}

	@GetMapping("/admin/flights")
	public String flightList(Model model) {

		List<Flight> flights = adminFlightService.getAllFlights();
		System.out.println("************************");
		
		for (Flight f : flights) {
		    System.out.println("Flight: " + f.getFlightNumber());

		    if (f.getAirline() == null) {
		        System.out.println("Airline = NULL");
		    } else {
		        System.out.println("Airline = " + f.getAirline().getAirlineName());
		    }
		}
		model.addAttribute("flights", flights);
	    model.addAttribute("activeTab", "flights");

		return "Admin/admin";
	}

}
