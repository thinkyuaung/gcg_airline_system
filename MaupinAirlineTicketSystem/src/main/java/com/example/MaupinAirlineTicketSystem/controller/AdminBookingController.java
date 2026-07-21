package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.service.AdminBookingService;

@Controller
@RequestMapping("/airline")
public class AdminBookingController {

	@Autowired
	AdminBookingService adminBookingService;

	@GetMapping("/admin/bookings")
	public String bookingList(Model model) {

		List<Booking> bookings = adminBookingService.getAllBookings();

		model.addAttribute("bookings", bookings);
		model.addAttribute("activeTab", "bookings");

		return "Admin/admin";
	}

	@GetMapping("/admin/booking/approve/{id}")
	public String approveBooking(@PathVariable("id") int id) {
		Booking booking = adminBookingService.getBookingById(id);
		if (booking != null) {
			booking.setStatus("Successful");
			adminBookingService.saveBooking(booking);
		}
		return "redirect:/airline/admin/bookings";
	}

	@GetMapping("/admin/booking/issue/{id}")
	public String issueBooking(@PathVariable("id") int id) {
		Booking booking = adminBookingService.getBookingById(id);
		if (booking != null) {
			booking.setStatus("Issued");
			adminBookingService.saveBooking(booking);
		}
		return "redirect:/airline/admin/bookings";
	}
}
