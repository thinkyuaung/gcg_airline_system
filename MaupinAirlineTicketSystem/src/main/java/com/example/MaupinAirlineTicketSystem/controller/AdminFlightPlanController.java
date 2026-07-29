package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MaupinAirlineTicketSystem.entity.Airport;
import com.example.MaupinAirlineTicketSystem.entity.Flight;
import com.example.MaupinAirlineTicketSystem.entity.FlightPlan;
import com.example.MaupinAirlineTicketSystem.service.AdminAirportService;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightPlanService;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/airline")
public class AdminFlightPlanController {

	@Autowired
	AdminFlightPlanService adminFlightPlanService;

	@Autowired
	AdminFlightService adminFlightService;

	@Autowired
	AdminAirportService adminAirportService;

	@GetMapping("/admin/flightPlanForm")
	public String create(Model model) {
		FlightPlan fp = new FlightPlan();
		List<Flight> flights = adminFlightService.getAllFlights();
		List<Airport> airports = adminAirportService.getAllAirports();

		model.addAttribute("flightPlan", fp);
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);

		return "Admin/AddFlightPlan";
	}

	@PostMapping("/admin/flightPlan")
	public String saveFlightPlan(@Valid @ModelAttribute("flightPlan") FlightPlan flightPlan, BindingResult result,
			Model model) {

		List<Flight> flights = adminFlightService.getAllFlights();
		List<Airport> airports = adminAirportService.getAllAirports();
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);

		if (result.hasErrors()) {
			return "Admin/AddFlightPlan";
		}

		adminFlightPlanService.saveFlightPlan(flightPlan);
		return "redirect:/airline/admin/flightPlans";
	}

	@GetMapping("/admin/flightPlans")
	public String flightPlanList(Model model) {
		List<FlightPlan> flightPlans = adminFlightPlanService.getAllFlightPlans();

		model.addAttribute("flightPlans", flightPlans);
		model.addAttribute("activeTab", "flightPlans");

		return "Admin/admin";
	}

	@GetMapping("/admin/flightPlan/edit/{id}")
	public String editFlightPlan(@PathVariable("id") int id, Model model) {
		FlightPlan flightPlan = adminFlightPlanService.getFlightPlanById(id);
		List<Flight> flights = adminFlightService.getAllFlights();
		List<Airport> airports = adminAirportService.getAllAirports();

		model.addAttribute("flightPlan", flightPlan);
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);

		return "Admin/AddFlightPlan";
	}

	@PostMapping("/admin/flightPlan/update")
	public String updateFlightPlan(@Valid @ModelAttribute("flightPlan") FlightPlan flightPlan, BindingResult result,
			Model model) {

		List<Flight> flights = adminFlightService.getAllFlights();
		List<Airport> airports = adminAirportService.getAllAirports();
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);

		if (result.hasErrors()) {
			return "Admin/AddFlightPlan";
		}

		adminFlightPlanService.saveFlightPlan(flightPlan);
		return "redirect:/airline/admin/flightPlans";
	}

	@GetMapping("/admin/flightPlan/delete/{id}")
	public String deleteFlightPlan(@PathVariable("id") int id) {
		adminFlightPlanService.deleteFlightPlanById(id);
		return "redirect:/airline/admin/flightPlans";
	}

}
