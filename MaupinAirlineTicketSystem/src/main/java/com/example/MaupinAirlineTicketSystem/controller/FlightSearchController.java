package com.example.MaupinAirlineTicketSystem.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.repository.AirportRepository;
import com.example.MaupinAirlineTicketSystem.repository.SeatClassRepository;
import com.example.MaupinAirlineTicketSystem.service.FlightSearchService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/airline")
public class FlightSearchController {
	
	@Autowired
	private FlightSearchService flightSearchService;
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	private SeatClassRepository seatClassRepository;

	@GetMapping("/search")
	public String searchFlights(
			@RequestParam(value = "departureCity", required = false) String departureCity,
			@RequestParam(value = "arrivalCity", required = false) String arrivalCity,
			@RequestParam(value = "departureDate", required = false) LocalDate departureDate,
			@RequestParam(value = "passengers", required = false, defaultValue = "1") int passengers,
			@RequestParam(value = "seatClass", required = false) String seatClass,
			HttpSession session,
			Model model) {

		model.addAttribute("airports", airportRepository.findAll());
		model.addAttribute("seatClasses", seatClassRepository.findAll());

		if (departureCity == null || departureCity.isBlank()) {
			return "userview/searchFlight";
		}

		session.setAttribute("searchDepartureCity", departureCity);
		session.setAttribute("searchArrivalCity", arrivalCity);
		session.setAttribute("searchDepartureDate",
				departureDate != null ? departureDate.toString() : null);

		List<FlightPlan> flightPlans =
				flightSearchService.searchFlights(
				        departureCity,
				        arrivalCity,
				        departureDate
				);

		model.addAttribute("flightPlans", flightPlans);

		model.addAttribute("departureCity", departureCity);
		model.addAttribute("arrivalCity", arrivalCity);
		model.addAttribute("departureDate", departureDate);

		model.addAttribute("passengers", passengers);
		model.addAttribute("seatClass", seatClass);

		model.addAttribute("airlines", flightSearchService.getAllAirlines());

		model.addAttribute("totalResults", flightPlans.size());
		return "userview/searchResult";
	}
}
