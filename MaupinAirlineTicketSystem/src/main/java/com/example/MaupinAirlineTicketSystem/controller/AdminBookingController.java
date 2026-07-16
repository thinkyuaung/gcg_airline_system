package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MaupinAirlineTicketSystem.entity.Flight;

@Controller
@RequestMapping("/airline")
public class AdminBookingController {

	@GetMapping("/admin/bookings")
	public String flightList(Model model) {

		//List<Flight> flights = adminFlightService.getAllFlights();
		
	//	model.addAttribute("flights", flights);
	    model.addAttribute("activeTab", "bookings");

		return "Admin/admin";
	}
}
