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
import com.example.MaupinAirlineTicketSystem.entity.Promotion;
import com.example.MaupinAirlineTicketSystem.repository.AdminFlightPlanRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminAirportService;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightPlanService;
import com.example.MaupinAirlineTicketSystem.service.AdminFlightService;
import com.example.MaupinAirlineTicketSystem.service.AdminPromotionService;

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

	@Autowired
	AdminPromotionService adminPromotionService;

	@Autowired
	AdminFlightPlanRepository adminFlightPlanRepository;

	private boolean hasDuplicateFlightPlan(FlightPlan flightPlan) {
		if (flightPlan.getFlight() == null || flightPlan.getFlight_date() == null
				|| flightPlan.getDepartureTime() == null || flightPlan.getArrivalTime() == null) {
			return false;
		}
		return adminFlightPlanRepository
				.findByFlightIdAndFlightDate(flightPlan.getFlight().getFlightId(), flightPlan.getFlight_date())
				.stream()
				.filter(fp -> fp.getFlightPlanId() != flightPlan.getFlightPlanId())
				.anyMatch(fp -> fp.getDepartureTime() != null && fp.getArrivalTime() != null
						&& flightPlan.getDepartureTime().isBefore(fp.getArrivalTime())
						&& flightPlan.getArrivalTime().isAfter(fp.getDepartureTime()));
	}

	@GetMapping("/admin/flightPlanForm")
	public String create(Model model) {
		FlightPlan fp = new FlightPlan();
		List<Flight> flights = adminFlightService.getAllFlights();
		List<Airport> airports = adminAirportService.getAllAirports();
		List<Promotion> promotions = adminPromotionService.getAllPromotions();

		model.addAttribute("flightPlan", fp);
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);
		model.addAttribute("promotions", promotions);

		return "Admin/AddFlightPlan";
	}

	@PostMapping("/admin/flightPlan")
	public String saveFlightPlan(@Valid @ModelAttribute("flightPlan") FlightPlan flightPlan, BindingResult result,
			Model model) {

		List<Flight> flights = adminFlightService.getAllFlights();
		List<Airport> airports = adminAirportService.getAllAirports();
		List<Promotion> promotions = adminPromotionService.getAllPromotions();
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);
		model.addAttribute("promotions", promotions);

		if (result.hasErrors()) {
			return "Admin/AddFlightPlan";
		}

		if (hasDuplicateFlightPlan(flightPlan)) {
			result.rejectValue("departureTime", "error.departureTime",
					"A flight plan for this flight already exists at an overlapping time on this date.");
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
		List<Promotion> promotions = adminPromotionService.getAllPromotions();

		model.addAttribute("flightPlan", flightPlan);
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);
		model.addAttribute("promotions", promotions);

		return "Admin/AddFlightPlan";
	}

	@GetMapping("/admin/flightPlan/detail/{id}")
	public String flightPlanDetail(@PathVariable("id") int id, Model model) {
		FlightPlan flightPlan = adminFlightPlanService.getFlightPlanById(id);
		if (flightPlan != null) {
			model.addAttribute("flightPlan", flightPlan);
		}
		return "Admin/flightPlanDetail";
	}

	@PostMapping("/admin/flightPlan/update")
	public String updateFlightPlan(@Valid @ModelAttribute("flightPlan") FlightPlan flightPlan, BindingResult result,
			Model model) {

		List<Flight> flights = adminFlightService.getAllFlights();
		List<Airport> airports = adminAirportService.getAllAirports();
		List<Promotion> promotions = adminPromotionService.getAllPromotions();
		model.addAttribute("flights", flights);
		model.addAttribute("airports", airports);
		model.addAttribute("promotions", promotions);

		if (result.hasErrors()) {
			return "Admin/AddFlightPlan";
		}

		if (hasDuplicateFlightPlan(flightPlan)) {
			result.rejectValue("departureTime", "error.departureTime",
					"A flight plan for this flight already exists at an overlapping time on this date.");
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
