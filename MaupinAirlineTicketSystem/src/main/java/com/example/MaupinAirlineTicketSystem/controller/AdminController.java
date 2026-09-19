package com.example.MaupinAirlineTicketSystem.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MaupinAirlineTicketSystem.entity.Airline;
import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.Flight;
import com.example.MaupinAirlineTicketSystem.repository.AdminAirlineRepository;
import com.example.MaupinAirlineTicketSystem.repository.AdminBookingRepository;
import com.example.MaupinAirlineTicketSystem.repository.AdminFlightRepository;
import com.example.MaupinAirlineTicketSystem.repository.UserRepository;

@Controller
@RequestMapping("/airline")
public class AdminController {

	@Autowired
	private AdminAirlineRepository airlineRepository;

	@Autowired
	private AdminFlightRepository flightRepository;

	@Autowired
	private AdminBookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/admin")
	public String adminPage(Model model) {

		List<Airline> airlines = airlineRepository.findAll();
		List<Flight> flights = flightRepository.findAll();
		List<Booking> bookings = bookingRepository.findAll();

		model.addAttribute("availableAirlines", airlines.size());
		model.addAttribute("availableFlights", flights.size());
		model.addAttribute("totalBookings", bookings.size());
		model.addAttribute("totalEarnings", bookingRepository.sumEarnings());
		model.addAttribute("totalUsers", userRepository.count());

		if (!airlines.isEmpty()) {
			model.addAttribute("lastlyAddedAirline", airlines.get(airlines.size() - 1).getAirlineName());
		}
		if (!flights.isEmpty()) {
			model.addAttribute("lastlyAddedFlight", flights.get(flights.size() - 1).getFlightNumber());
		}

		// Bookings per month (line chart)
		DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("yyyy-MM");
		Map<String, Long> bookingsByMonth = bookings.stream()
				.filter(b -> b.getBookingDate() != null)
				.collect(Collectors.groupingBy(
					b -> b.getBookingDate().format(monthFmt),
					LinkedHashMap::new,
					Collectors.counting()
				));
		model.addAttribute("chartBookingMonthLabels", new ArrayList<>(bookingsByMonth.keySet()));
		model.addAttribute("chartBookingMonthData", new ArrayList<>(bookingsByMonth.values()));

		// Booking status breakdown (doughnut chart)
		Map<String, Long> bookingsByStatus = bookings.stream()
				.collect(Collectors.groupingBy(
					b -> b.getStatus() != null ? b.getStatus() : "Unknown",
					Collectors.counting()
				));
		model.addAttribute("chartBookingStatusLabels", new ArrayList<>(bookingsByStatus.keySet()));
		model.addAttribute("chartBookingStatusData", new ArrayList<>(bookingsByStatus.values()));

		return "Admin/admin";
	}

}
