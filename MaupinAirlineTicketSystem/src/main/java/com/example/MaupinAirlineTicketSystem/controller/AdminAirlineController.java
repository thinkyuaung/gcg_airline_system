package com.example.MaupinAirlineTicketSystem.controller;

import java.io.File;
import java.io.IOException;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Airport;
import com.example.MaupinAirlineTicketSystem.entity.Flight;
import com.example.MaupinAirlineTicketSystem.service.AdminAirlineService;
import com.example.MaupinAirlineTicketSystem.service.AdminAirportService;

@Controller
@RequestMapping("/airline") // Base Path
public class AdminAirlineController {

	@Autowired
	private AdminAirlineService adminAirlineService;

	@Autowired
	private AdminAirportService adminAirportService;

	/*
	 * // =====  DASHBOARD METHOD=====
	 * 
	 * @GetMapping("/admin") public String adminDashboard(Model model) { //
	 * List<Airline> airlineList = adminAirlineService.viewAllFlight();
	 * List<Airport> airportList = adminAirportService.getAllAirports();
	 * 
	 * //model.addAttribute("airlines", airlineList); model.addAttribute("airports",
	 * airportList);
	 * 
	 * return "Admin/admin"; }
	 */

	// ===== ✈ AIRLINE METHODS =====
	@GetMapping("/admin/airlineForm")
	public String create(Model model) {
		Airline a = new Airline();
		model.addAttribute("airline", a);
		return "Admin/AddAirline";
	}

	@PostMapping("/admin/airline")
	public String saveSAirline(@ModelAttribute("airline") Airline airline,
			@RequestParam("photoFile") MultipartFile photoFile) throws IllegalStateException, IOException {

		if (!photoFile.isEmpty()) {
			String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
			File directory = new File(uploadDir);

			if (!directory.exists()) {
				directory.mkdir();
			}

			String fileName = System.currentTimeMillis() + "_" + photoFile.getOriginalFilename();
			File destination = new File(directory, fileName);
			photoFile.transferTo(destination);

			airline.setLogo(fileName);
		}

		adminAirlineService.saveFlight(airline);
		return "redirect:/airline/admin";
	}

	// ===== AIRPORT METHODS =====
	@GetMapping("/admin/airportForm")
	public String createAirport(Model model) {
		Airport airport = new Airport();
		model.addAttribute("airport", airport);
		return "Admin/AddAirport";
	}

	@PostMapping("/admin/airport")
	public String saveAirport(@ModelAttribute("airport") Airport airport) {
		adminAirportService.saveAirport(airport);
		return "redirect:/airline/admin";
	}
	
	@GetMapping("/admin/airports")
	public String getAllAirports(Model model) {

		List<Airport> airport = adminAirportService.getAllAirports();
		model.addAttribute("airport", airport);
	    model.addAttribute("activeTab", "airport");

		return "Admin/admin";
	}
	
	
	
	
}