package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.service.AdminBookingService;
import com.example.MaupinAirlineTicketSystem.service.EmailService;

@Controller
@RequestMapping("/airline")
public class AdminBookingController {

	@Autowired
	AdminBookingService adminBookingService;

	@Autowired
	EmailService emailService;

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
			String serialCode = "MAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
			booking.setBookingCode(serialCode);
			booking.setStatus("Success");
			adminBookingService.saveBooking(booking);

			emailService.sendTicketEmail(booking, serialCode);
		}
		return "redirect:/airline/admin/bookings";
	}

	@PostMapping("/admin/booking/issue")
	public String issueBooking(@RequestParam("bookingId") int id,
			@RequestParam(value = "description", required = false) String description) {
		Booking booking = adminBookingService.getBookingById(id);
		if (booking != null) {
			booking.setStatus("Issued");
			booking.setDescription(description);
			adminBookingService.saveBooking(booking);
		}
		return "redirect:/airline/admin/bookings";
	}
}
