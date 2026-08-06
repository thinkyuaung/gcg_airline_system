package com.example.MaupinAirlineTicketSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MaupinAirlineTicketSystem.entity.Booking;
import com.example.MaupinAirlineTicketSystem.entity.BookingDetail;
import com.example.MaupinAirlineTicketSystem.repository.BookingDetailRepository;
import com.example.MaupinAirlineTicketSystem.service.AdminBookingService;

@Controller
@RequestMapping("/airline")
public class ManageBookingController {

	@Autowired
	AdminBookingService adminBookingService;

	@Autowired
	BookingDetailRepository bookingDetailRepository;

	@GetMapping("/manage-booking")
	public String showManageBookingPage(Model model) {
		 model.addAttribute("activeTab", "manage");
		return "manage-booking";
	}

	@PostMapping("/manage-booking")
	public String searchBooking(@RequestParam("serialCode") String serialCode,
			@RequestParam("lastName") String lastName, Model model) {

		Booking booking = adminBookingService.findByBookingCodeAndUserLastName(serialCode.trim(), lastName.trim());

		if (booking == null) {
			model.addAttribute("error", "No booking found. Please check your Serial Code and Last Name.");
			model.addAttribute("activeTab", "manage");
			return "manage-booking";
		}

		List<BookingDetail> passengerDetails =
				bookingDetailRepository.findByBooking_BookingId(booking.getBookingId());

		model.addAttribute("booking", booking);
		model.addAttribute("passengerDetails", passengerDetails);
		model.addAttribute("activeTab", "manage");
		return "manage-booking";
	}

}
